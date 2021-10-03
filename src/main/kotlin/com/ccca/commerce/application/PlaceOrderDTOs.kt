package com.ccca.commerce.application

import com.ccca.commerce.domain.entity.Cpf
import com.ccca.commerce.domain.entity.OrderCode
import com.ccca.commerce.domain.entity.OrderItem
import java.time.LocalDate

data class PlaceOrderInputDto(
    val cpf: Cpf,
    val zipcode: String,
    val items: List<OrderItem>,
    val coupon: String,
    val issueDate: LocalDate = LocalDate.now()
)

data class PlaceOrderInputRESTDto(
    val cpf: Cpf,
    val zipcode: String,
    val items: List<OrderItem>,
    val coupon: String
) {
    fun toDTO() = PlaceOrderInputDto(cpf,zipcode,items,coupon)
}

data class PlaceOrderOutputDto(
    val total: Long,
    val shippingPrice: Double,
    val code: OrderCode,
    val taxes: Double = 0.0
)