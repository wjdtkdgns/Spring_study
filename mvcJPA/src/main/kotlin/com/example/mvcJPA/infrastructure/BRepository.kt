package com.example.mvcJPA.infrastructure

import com.example.mvcJPA.domain.B
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BRepository : JpaRepository<B, Long>