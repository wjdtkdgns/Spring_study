package com.example.api.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CouponCreateProducer (
    private val kafkaTemplate: KafkaTemplate<String, Long>
){
    fun create(uid: Long){
//        println("create event")
        kafkaTemplate.send("coupon_create", uid)
    }
}