package com.ccca.commerce.application.getOrder

import com.ccca.commerce.domain.repository.port.OrderRepository
import org.springframework.stereotype.Component

@Component
class GetOrderUseCase(
    private val orderRepository: OrderRepository
) : GetOrderPort {

    override fun execute(code: String): GetOrderOutput {

        val orderFound = orderRepository.findByCode(code) ?: run { throw Error("Order not found") }

        return orderFound.toOutputDto(orderFound.items)
    }

}