package com.example.webfluxCoroutineJPA.domain

import jakarta.persistence.*

@Entity
@Table(name = "c")
class C(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    val aid: Long,
    val varchar256: String,
    val varchar512: String,
    val intt: Long,
    val bigintt: Long,
) : BaseEntity()