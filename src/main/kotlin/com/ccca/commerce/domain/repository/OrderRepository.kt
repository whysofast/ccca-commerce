package com.ccca.commerce.domain.repository

import com.ccca.commerce.domain.entity.Order

interface OrderRepository {
    fun save(order: Order): Order
    fun count(): Int
}