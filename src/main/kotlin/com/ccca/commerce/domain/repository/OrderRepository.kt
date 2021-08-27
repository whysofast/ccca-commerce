package com.ccca.commerce.domain.repository

import com.ccca.commerce.domain.entity.Order

// (Port) -> Adapter -> Repository <= JpaRepository
interface OrderRepository {
    fun save(order: Order): Order
    fun count(): Int
    fun findByCode(code: String): Order?
}