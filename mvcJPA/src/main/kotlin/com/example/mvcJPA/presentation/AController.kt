package com.example.mvcJPA.presentation

import com.example.mvcJPA.application.AService
import com.example.mvcJPA.extension.wrapOk
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class AController(
    private val aService: AService,
) {
    @GetMapping("randomAById")
    fun getRandomAById() = aService.getRandomAById().wrapOk()

    @GetMapping("randomAById-withB")
    fun getRandomAByIdWithB() = aService.getRandomAByIdWithB().wrapOk()

    @GetMapping("randomAById-withC")
    fun getRandomAByIdWithC() = aService.getRandomAByIdWithC().wrapOk()

    @GetMapping("randomAByIntt")
    fun getRandomAByIntt() = aService.getRandomAByIntt().wrapOk()

    @GetMapping("randomAByIntt-withB")
    fun getRandomAByInttWithB() = aService.getRandomAByInttWithB().wrapOk()

    @GetMapping("randomAByIntt-withC")
    fun getRandomAByInttWithC() = aService.getRandomAByInttWithC().wrapOk()
}