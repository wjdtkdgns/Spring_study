package com.example.mvcJPA.application

import com.example.mvcJPA.infrastructure.ARepository
import org.springframework.scheduling.config.Task
import org.springframework.stereotype.Service

@Service
class AService(
    private val aRepository: ARepository,
) {
    fun getRandomAById(): Any? {
        return aRepository.findById((1..2000).random().toLong())
    }

    fun getRandomAByIdWithB() : Any? {
        return aRepository.getRandomAByIdWithB((1..2000).random().toLong())
    }

    fun getRandomAByIdWithC(): Any? {
        return aRepository.getRandomAByIdWithC((1..2000).random().toLong())

    }

    fun getRandomAByIntt(): Any? {
        return aRepository.findAllByIntt((1..2000).random().toLong())

    }

    fun getRandomAByInttWithB(): Any? {
        return aRepository.getRandomAByInttWithB((1..2000).random().toLong())

    }

    fun getRandomAByInttWithC(): Any? {
        return aRepository.getRandomAByInttWithC((1..2000).random().toLong())

    }

    fun quit(): Any? {
        Thread.sleep(1000)
        return null
    }
}