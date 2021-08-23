package com.ccca.commerce

import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PlaceOrderUseCase(
    private val shippingCalculator: ShippingCalculatorPort,
    private val zipcodeCalculatorAPIPort: ZipcodeCalculatorAPIPort
) {

    private val coupons = listOf(
        Coupon("VALE20", 20.0, LocalDateTime.of(2022, 1, 1, 0, 0, 0)),
        Coupon("VALE20EXPIRADO", 20.0, LocalDateTime.of(2020, 1, 1, 0, 0, 0))
    )

    private val items = listOf(
        Item("1", "Mouse", 100, 50, 50, 50, 22),
        Item("2", "Teclado", 200, 50, 50, 50, 22),
        Item("3", "Monitor", 500, 50, 50, 50, 22),
        Item("4", "Clips", 5, 1, 1, 1, 1)
    )

    fun execute(orderInputDto: OrderInputDto): OrderOutputDto {
        val order = Order(orderInputDto.cpf)

        val distance = zipcodeCalculatorAPIPort.calculate("11111111", orderInputDto.zipcode)

        orderInputDto.items.map { input ->
            val itemFound = this.items.find { item -> item.id == input.id }

            itemFound
                ?.let {
                    order.addItem(input.id, it.price, input.quantity)
                    order.shippingPrice += shippingCalculator.calculate(distance, it) * input.quantity
                }
                ?: run { throw Error("Item not found") }
        }

        val foundCoupon = this.coupons.find { coupon -> orderInputDto.coupon == coupon.name }
        foundCoupon?.let { order.addCoupon(it) }

        return OrderOutputDto(order.getTotal(), order.shippingPrice)
    }

}