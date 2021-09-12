package com.ccca.commerce.application.getOrder

import com.ccca.commerce.domain.entity.Order
import com.ccca.commerce.domain.entity.OrderItem

class GetOrderOutput(
    val code: String,
    val shippingPrice: Double,
    val total: Long,
    val orderItems: MutableList<OrderItem>
)

fun Order.toOutputDto(orderItems: MutableList<OrderItem>) = GetOrderOutput(
    code = code.value,
    shippingPrice = shippingPrice,
    total = getTotal(),
    orderItems = orderItems
)