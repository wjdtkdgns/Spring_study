package com.example.consumer.consumer

import com.example.consumer.domain.Coupon
import com.example.consumer.domain.FailedEvent
import com.example.consumer.repository.CouponRepository
import com.example.consumer.repository.FailedEventRepository
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class CouponCreateConsumer(
    private val couponReposiory: CouponRepository,
    private val failedEventRepository: FailedEventRepository,
) {
    val logger = LoggerFactory.getLogger(CouponCreateConsumer::class.java)

    @KafkaListener(topics = ["coupon_create"], groupId = "group_1")
    fun listener(uid: Long) {
        try {
            Coupon(uid = uid).run { couponReposiory.save(this) }
        } catch (e: Exception) {
            logger.error("fail to create coupon::${uid}")
            FailedEvent(uid = uid).run { failedEventRepository.save(this) }
        }
    }
}