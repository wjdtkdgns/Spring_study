package com.example.api.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository

@Repository
class CouponCountRepository(
    private val redisTemplate: StringRedisTemplate
) {
    fun increment(): Long {
        return redisTemplate.opsForValue()
            .increment("coupon_count") ?: throw RuntimeException("redis execution fail")
    }
}