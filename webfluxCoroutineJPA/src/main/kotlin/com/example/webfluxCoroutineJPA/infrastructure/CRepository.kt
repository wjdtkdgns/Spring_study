package com.example.webfluxCoroutineJPA.infrastructure

import com.example.webfluxCoroutineJPA.domain.C
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CRepository : JpaRepository<C, Long>