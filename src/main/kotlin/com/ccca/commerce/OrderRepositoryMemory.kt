package com.ccca.commerce

import org.springframework.stereotype.Component

@Component
class OrderRepositoryMemory : OrderRepository {

    private val orders = mutableListOf<Order>()

    override fun save(order: Order): Order {
        orders.add(order)
        return orders.last()
    }

}