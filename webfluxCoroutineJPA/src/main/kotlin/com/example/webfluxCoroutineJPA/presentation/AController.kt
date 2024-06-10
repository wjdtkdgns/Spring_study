package com.example.webfluxCoroutineJPA.presentation

import com.example.webfluxCoroutineJPA.application.AService
import com.example.webfluxCoroutineJPA.extension.wrapOk
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class AController(
    private val aService: AService,
) {
//    @GetMapping("initDB")
//    suspend fun initDB() = aService.initDB().wrapOk()

    @GetMapping("randomAById")
    suspend fun getRandomAById() = aService.getRandomAById().wrapOk()

    @GetMapping("randomAById-withB")
    suspend fun getRandomAByIdWithB() = aService.getRandomAByIdWithB().wrapOk()

    @GetMapping("randomAById-withC")
    suspend fun getRandomAByIdWithC() = aService.getRandomAByIdWithC().wrapOk()

    @GetMapping("randomAByIntt")
    suspend fun getRandomAByIntt() = aService.getRandomAByIntt().wrapOk()

    @GetMapping("randomAByIntt-withB")
    suspend fun getRandomAByInttWithB() = aService.getRandomAByInttWithB().wrapOk()

    @GetMapping("randomAByIntt-withC")
    suspend fun getRandomAByInttWithC() = aService.getRandomAByInttWithC().wrapOk()
}