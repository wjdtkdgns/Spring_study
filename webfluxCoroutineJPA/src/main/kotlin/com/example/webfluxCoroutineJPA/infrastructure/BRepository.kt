package com.example.webfluxCoroutineJPA.infrastructure

import com.example.webfluxCoroutineJPA.domain.B
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BRepository : JpaRepository<B, Long>