package com.ccca.commerce

class OrderInputDto(
    val cpf : Cpf,
    val items : List<OrderItem>,
    val coupon : String
) {
}

class OrderOutputDto(
    val total : Long
)