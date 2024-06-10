package com.example.mvcJPA.domain

import jakarta.persistence.*

@Entity
@Table(name = "b")
class B(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    val aid: Long,
    val varchar256: String,
    val varchar512: String,
    val intt: Long,
    val bigintt: Long,
) : BaseEntity()