package com.ccca.commerce.infra.repository.adapter.memory

import com.ccca.commerce.domain.entity.Order
import com.ccca.commerce.domain.repository.port.OrderRepository
import org.springframework.stereotype.Component

// Port -> (Adapter) -> Repository <= JpaRepository
@Component
class OrderRepositoryMemory : OrderRepository {

    private val orders = mutableListOf<Order>()

    override fun save(order: Order): Order {
        orders.add(order)
        return orders.last()
    }

    override fun count() = this.orders.size

    override fun findByCode(code: String) = orders.find { it.code.value == code }

    override fun clear() = this.orders.clear()

}