package com.ccca.commerce.application

import com.ccca.commerce.domain.repository.port.OrderRepository
import org.springframework.stereotype.Component

@Component
class GetOrderUseCase(
    private val orderRepository: OrderRepository
) {

    fun execute(code: String): GetOrderOutput {

        val orderFound = orderRepository.findByCode(code) ?: run { throw Error("Order not found") }

        return orderFound.toOutputDto(orderFound.items)
    }

}