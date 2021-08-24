package com.ccca.commerce

import org.springframework.stereotype.Component

@Component
class PlaceOrderUseCase(
    private val shippingCalculator: ShippingCalculatorPort,
    private val zipcodeCalculatorAPIPort: ZipcodeCalculatorAPIPort,
    private val itemRepository: ItemRepository,
    private val couponRepository: CouponRepository
) {
    
    fun execute(orderInputDto: OrderInputDto): OrderOutputDto {
        val order = Order(orderInputDto.cpf)

        val distance = zipcodeCalculatorAPIPort.calculate("11111111", orderInputDto.zipcode)

        orderInputDto.items.map { inputItem ->
            val itemFound = itemRepository.getById(inputItem.id)

            itemFound
                ?.let {
                    order.addItem(it.id, it.price, inputItem.quantity)
                    order.shippingPrice += shippingCalculator.calculate(distance, it) * inputItem.quantity
                }
                ?: run { throw Error("Item not found") }
        }

        val foundCoupon = couponRepository.findByName(orderInputDto.coupon)
        foundCoupon?.let { order.addCoupon(it) }

        return OrderOutputDto(order.getTotal(), order.shippingPrice)
    }

}