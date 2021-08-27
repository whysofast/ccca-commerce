package com.ccca.commerce.application

import com.ccca.commerce.domain.repository.CouponRepository
import com.ccca.commerce.domain.repository.ItemRepository
import com.ccca.commerce.domain.repository.OrderRepository
import org.springframework.stereotype.Component

@Component
class GetOrderUseCase(
    private val itemRepository: ItemRepository,
    private val couponRepository: CouponRepository,
    private val orderRepository: OrderRepository
) {

    fun execute(code: String): GetOrderOutput {

        val orderFound = orderRepository.findByCode(code) ?: run { throw Error("Order not found") }

        // Não é responsabilidade desse cara puxar os items pra criar o output. Poderá ser feito ao implementar o banco de
        // dados e reconstruir o toModel a partir dos objetos recuperados por conta das foreign keys.
        val orderItems = orderFound.items.map { item ->
            itemRepository.getById(item.id)
        }

        return orderFound.toOutputDto(orderItems)
    }

}