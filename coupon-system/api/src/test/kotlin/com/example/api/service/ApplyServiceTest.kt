package com.example.api.service

import com.example.api.repository.CouponRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
class ApplyServiceTest(
    @Autowired
    private val applyService: ApplyService,
    @Autowired
    private val couponRepository: CouponRepository,
) : FunSpec({
    context("응모 test"){
        test("한번만 응모"){
            applyService.apply(1)

            val count = couponRepository.count()

            count shouldBe 1
        }
    }

    context("다수 응모 test"){
        test("여러명 응모"){
            val threadCount = 1000
            val executorService = Executors.newFixedThreadPool(32)
            val latch = CountDownLatch(threadCount)

            for (i in 1L..threadCount) {
                executorService.submit {
                    try {
                        applyService.apply(i)
                    }
                    finally {
                        latch.countDown()
                    }
                }
            }

            latch.await()

            val count = couponRepository.count()

            count shouldBe 100
        }
    }
})
