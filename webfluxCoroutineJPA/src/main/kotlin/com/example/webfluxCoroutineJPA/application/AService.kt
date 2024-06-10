package com.example.webfluxCoroutineJPA.application

import com.example.webfluxCoroutineJPA.domain.A
import com.example.webfluxCoroutineJPA.domain.B
import com.example.webfluxCoroutineJPA.domain.C
import com.example.webfluxCoroutineJPA.infrastructure.ARepository
import com.example.webfluxCoroutineJPA.infrastructure.BRepository
import com.example.webfluxCoroutineJPA.infrastructure.CRepository
import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class AService(
    private val aRepository: ARepository,
    private val bRepository: BRepository,
    private val cRepository: CRepository,
) {
    suspend fun initDB() {
        val a = mutableListOf<A>()
        val b = mutableListOf<B>()
        val c = mutableListOf<C>()
        for (i in 1..2000) {
            A(
                varchar256 = UUID.randomUUID().toString(),
                varchar512 = UUID.randomUUID().toString(),
                intt = (1..2000).random().toLong(),
                bigintt = (1..2000).random().toLong(),
            ).run { a.add(this) }
        }
        for (i in 1..10000) {
            B(
                aid = (1..2000).random().toLong(),
                varchar256 = UUID.randomUUID().toString(),
                varchar512 = UUID.randomUUID().toString(),
                intt = (1..2000).random().toLong(),
                bigintt = (1..2000).random().toLong(),
            ).run { b.add(this) }

            C(
                aid = (1..2000).random().toLong(),
                varchar256 = UUID.randomUUID().toString(),
                varchar512 = UUID.randomUUID().toString(),
                intt = (1..2000).random().toLong(),
                bigintt = (1..2000).random().toLong(),
            ).run { c.add(this) }
        }

        coroutineScope {
            val aDeferred = async { aRepository.saveAll(a) }
            val bDeferred = async { bRepository.saveAll(b) }
            val cDeferred = async { cRepository.saveAll(c) }

            awaitAll(aDeferred, bDeferred, cDeferred)
        }
    }

    suspend fun getRandomAById(): Any? {
        return withContext(Dispatchers.IO) { aRepository.findById((1..2000).random().toLong()) }
    }

    suspend fun getRandomAByIdWithB() : Any? {
        return withContext(Dispatchers.IO) { aRepository.getRandomAByIdWithB((1..2000).random().toLong()) }
    }

    suspend fun getRandomAByIdWithC(): Any? {
        return withContext(Dispatchers.IO) { aRepository.getRandomAByIdWithC((1..2000).random().toLong()) }

    }

    suspend fun getRandomAByIntt(): Any? {
        return withContext(Dispatchers.IO) { aRepository.findAllByIntt((1..2000).random().toLong()) }

    }

    suspend fun getRandomAByInttWithB(): Any? {
        return withContext(Dispatchers.IO) { aRepository.getRandomAByInttWithB((1..2000).random().toLong()) }

    }

    suspend fun getRandomAByInttWithC(): Any? {
        return withContext(Dispatchers.IO) { aRepository.getRandomAByInttWithC((1..2000).random().toLong()) }

    }

    suspend fun quit(): Any? {
        delay(1000)
        return null
    }
}