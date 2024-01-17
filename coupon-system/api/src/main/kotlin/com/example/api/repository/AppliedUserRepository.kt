package com.example.api.repository

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository

@Repository
class AppliedUserRepository(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    fun add(uid: Long): Long? {
        return stringRedisTemplate.opsForSet()
            .add("applied_user", uid.toString())
    }
}