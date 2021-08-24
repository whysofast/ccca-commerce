package com.ccca.commerce.application

import com.ccca.commerce.domain.entity.Cpf
import com.ccca.commerce.domain.entity.OrderItem

data class PlaceOrderInputDto(
    val cpf: Cpf,
    val zipcode: String,
    val items: List<OrderItem>,
    val coupon: String
)

data class PlaceOrderOutputDto(
    val total: Long,
    val shippingPrice: Double
)