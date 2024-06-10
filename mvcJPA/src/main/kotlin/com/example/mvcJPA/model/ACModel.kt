package com.example.mvcJPA.model

import com.example.mvcJPA.domain.A
import com.example.mvcJPA.domain.C
import com.querydsl.core.annotations.QueryProjection

data class ACModel @QueryProjection constructor(
    val a: A,
    val c: C
)