package com.example.mvcJPA.model

import com.example.mvcJPA.domain.A
import com.example.mvcJPA.domain.B
import com.querydsl.core.annotations.QueryProjection

data class ABModel @QueryProjection constructor(
    val a: A,
    val b: B
)