package com.ccca.commerce.infra.repository.memory

import com.ccca.commerce.domain.entity.Order
import com.ccca.commerce.domain.repository.OrderRepository
import org.springframework.stereotype.Component

@Component
class OrderRepositoryMemory : OrderRepository {

    private val orders = mutableListOf<Order>()

    override fun save(order: Order): Order {
        orders.add(order)
        return orders.last()
    }

    override fun count() = this.orders.size

}