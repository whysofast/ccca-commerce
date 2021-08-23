package com.ccca.commerce

data class OrderInputDto(
    val cpf: Cpf,
    val zipcode: String,
    val items: List<OrderItem>,
    val coupon: String
) {
}

data class OrderOutputDto(
    val total: Long,
    val shippingPrice: Double
)