package com.example.webfluxCoroutineJPA.model

import com.example.webfluxCoroutineJPA.domain.A
import com.example.webfluxCoroutineJPA.domain.B
import com.querydsl.core.annotations.QueryProjection

data class ABModel @QueryProjection constructor(
    val a: A,
    val b: B
)