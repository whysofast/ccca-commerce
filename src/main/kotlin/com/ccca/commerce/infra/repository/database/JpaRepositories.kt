package com.ccca.commerce.infra.repository.database

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ItemJpaRepository : JpaRepository<ItemDBO, Long>

@Repository
interface CouponJpaRepository : JpaRepository<CouponDBO, String> {
    fun findByName(name: String): CouponDBO?
}

@Repository
interface OrderJpaRepository : JpaRepository<OrderDBO, Long> {
    @Transactional(readOnly = true)
    fun findByCode(code: String): OrderDBO?
}