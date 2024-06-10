package com.example.webfluxCoroutineJPA.infrastructure

import com.example.webfluxCoroutineJPA.domain.A
import com.example.webfluxCoroutineJPA.domain.QA
import com.example.webfluxCoroutineJPA.domain.QB
import com.example.webfluxCoroutineJPA.domain.QC
import com.example.webfluxCoroutineJPA.model.ABModel
import com.example.webfluxCoroutineJPA.model.ACModel
import com.example.webfluxCoroutineJPA.model.QABModel
import com.example.webfluxCoroutineJPA.model.QACModel
import com.querydsl.jpa.impl.JPAQuery
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ARepository : JpaRepository<A, Long>, ACustomRepository {
    @Transactional(readOnly = true)
    fun findAllByIntt(intt: Long): List<A>
}

interface ACustomRepository {
    @Transactional(readOnly = true)
    fun getRandomAByIdWithB(id: Long): List<ABModel>

    @Transactional(readOnly = true)
    fun getRandomAByIdWithC(id: Long): List<ACModel>

    @Transactional(readOnly = true)
    fun getRandomAByInttWithB(intt: Long): List<ABModel>

    @Transactional(readOnly = true)
    fun getRandomAByInttWithC(intt: Long): List<ACModel>
}

class ACustomRepositoryImpl : ACustomRepository, QuerydslRepositorySupport(A::class.java) {
    @Autowired
    @Qualifier("webfluxCoroutineJPAEntityManager")
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }

    private val qA = QA.a
    private val qB = QB.b
    private val qC = QC.c

    override fun getRandomAByIdWithB(id: Long): List<ABModel> {
        return JPAQuery<QA>(entityManager)
            .select(QABModel(qA, qB))
            .from(qA)
            .join(qB).on(qA.id.eq(qB.aid))
            .where(
                qA.id.eq(id)
            ).fetch()
    }

    override fun getRandomAByIdWithC(id: Long): List<ACModel> {
        return JPAQuery<QA>(entityManager)
            .select(QACModel(qA, qC))
            .from(qA)
            .join(qC).on(qA.id.eq(qC.aid))
            .where(
                qA.id.eq(id)
            ).fetch()
    }

    override fun getRandomAByInttWithB(intt: Long): List<ABModel> {
        return JPAQuery<QA>(entityManager)
            .select(QABModel(qA, qB))
            .from(qA)
            .join(qB).on(qA.intt.eq(qB.aid))
            .where(
                qA.intt.eq(intt)
            ).fetch()
    }

    override fun getRandomAByInttWithC(intt: Long): List<ACModel> {
        return JPAQuery<QA>(entityManager)
            .select(QACModel(qA, qC))
            .from(qA)
            .join(qC).on(qA.intt.eq(qC.aid))
            .where(
                qA.intt.eq(intt)
            ).fetch()
    }

}
