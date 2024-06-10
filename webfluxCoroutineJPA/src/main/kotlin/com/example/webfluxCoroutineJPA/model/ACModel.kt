package com.example.webfluxCoroutineJPA.model

import com.example.webfluxCoroutineJPA.domain.A
import com.example.webfluxCoroutineJPA.domain.C
import com.querydsl.core.annotations.QueryProjection

data class ACModel @QueryProjection constructor(
    val a: A,
    val c: C
)