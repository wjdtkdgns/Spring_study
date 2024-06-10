package com.example.mvcJPA.domain

import jakarta.persistence.*

@Entity
@Table(name = "a")
class A(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    val varchar256: String,
    val varchar512: String,
    val intt: Long,
    val bigintt: Long,
) : BaseEntity()