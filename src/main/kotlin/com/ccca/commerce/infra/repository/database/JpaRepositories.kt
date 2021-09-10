package com.ccca.commerce.infra.repository.database

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemJpaRepository : JpaRepository<ItemDBO, Long>

@Repository
interface CouponJpaRepository : JpaRepository<CouponDBO, String> {
    fun findByName(name: String): CouponDBO?
}

@Repository
interface OrderJpaRepository : JpaRepository<OrderDBO, Long> {
    fun findByCode(code: String): OrderDBO?
}

@Repository
interface OrderItemJpaRepository : JpaRepository<OrderItemDBO, String>

@Repository
interface TaxTableJpaRepository : JpaRepository<TaxTableDBO, Long> {
    fun findAllByItemId(itemId: Long): List<TaxTableDBO?>
}
