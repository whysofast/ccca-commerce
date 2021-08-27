package com.ccca.commerce.application

import com.ccca.commerce.domain.entity.Item
import com.ccca.commerce.domain.entity.Order

class GetOrderOutput(
    val code: String,
    val shippingPrice: Double,
    val total: Long,
    val orderItems: List<Item?>
)

fun Order.toOutputDto(orderItems: List<Item?>) = GetOrderOutput(
    code = code.value,
    shippingPrice = shippingPrice,
    total = getTotal(),
    orderItems = orderItems
)