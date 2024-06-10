package com.example.mvcJPA.infrastructure

import com.example.mvcJPA.domain.C
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CRepository : JpaRepository<C, Long>