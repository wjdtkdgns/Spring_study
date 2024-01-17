package com.example.api.service

import com.example.api.domain.Coupon
import com.example.api.producer.CouponCreateProducer
import com.example.api.repository.AppliedUserRepository
import com.example.api.repository.CouponCountRepository
import com.example.api.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
class ApplyService(
    private val couponRepository: CouponRepository,
    private val couponCountRepository: CouponCountRepository,
    private val couponCreateProducer: CouponCreateProducer,
    private val appliedUserRepository: AppliedUserRepository,
) {
    // race condition 발생 -> count 값에 대해 동시 접근해서
//    fun apply(uid: Long){
//        val count = couponRepository.count();
//
//        if(count>100)
//            return
//
//        Coupon(uid = uid).run { couponRepository.save(this) }
//    }

    // redis 싱글 쓰레드 이용 -> 성능 떨어짐
    // api lock -> 성능 떨어짐
    // 이 로직은 RDB 부하감 -> 서비스 latency 증가 가능
//    fun apply(uid: Long){
//        val count = couponCountRepository.increment()
//
//        if(count>100)
//            return
//
//        Coupon(uid = uid).run { couponRepository.save(this) }
//    }

    // kafka
    fun apply(uid: Long) {
        val apply = appliedUserRepository.add(uid)

        if (apply != 1L){
            return
        }

        val count = couponCountRepository.increment()

        if (count > 100) {
            return
        }

//        Coupon(uid = uid).run { couponRepository.save(this) }
        couponCreateProducer.create(uid)
    }
}