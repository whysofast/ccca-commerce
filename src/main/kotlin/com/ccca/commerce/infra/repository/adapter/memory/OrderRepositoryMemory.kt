package com.ccca.commerce.infra.repository.adapter.memory

import com.ccca.commerce.domain.entity.Order
import com.ccca.commerce.domain.repository.port.OrderRepository
import com.ccca.commerce.infra.repository.database.CouponJpaRepository
import com.ccca.commerce.infra.repository.database.OrderJpaRepository
import com.ccca.commerce.infra.repository.database.toDBO
import org.springframework.stereotype.Component

// Port -> (Adapter) -> Repository <= JpaRepository
@Component
class OrderRepositoryMemory(
    private val orderJpaRepository: OrderJpaRepository,
    private val couponJpaRepository: CouponJpaRepository
) : OrderRepository {

    override fun save(order: Order): Order {
        val foundCoupon = order.coupon?.let { couponJpaRepository.findByName(it.name) }
        val savedOrder = orderJpaRepository.save(order.toDBO(foundCoupon))
        return savedOrder.toModel()
    }

    override fun count(): Int {
        return orderJpaRepository.count().toInt()
    }


    override fun findByCode(code: String): Order? {
        val foundOrder = orderJpaRepository.findByCode(code)
        return foundOrder?.toModel()
    }


    override fun clear() {
        orderJpaRepository.deleteAll()
    }

}