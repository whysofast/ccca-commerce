package com.ccca.commerce

import org.springframework.stereotype.Component

@Component
class PlaceOrderUseCase() {

    private val coupons = listOf(Coupon("VALE20",20.0))

    fun execute(orderInputDto: OrderInputDto): OrderOutputDto {
        val order = Order(orderInputDto.cpf)

        orderInputDto.items.map {
            order.addItem(it.description, it.price, it.quantity)
        }

        val foundCoupon = this.coupons.find { coupon -> orderInputDto.coupon == coupon.name }
        foundCoupon?.let { order.addCoupon(it) }

        return OrderOutputDto(order.getTotal())
    }

}