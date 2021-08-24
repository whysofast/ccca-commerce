package com.ccca.commerce

import org.springframework.stereotype.Component

@Component
class PlaceOrderUseCase(
    private val shippingCalculator: ShippingCalculatorPort,
    private val zipcodeCalculatorAPIPort: ZipcodeCalculatorAPIPort,
    private val itemRepository: ItemRepository,
    private val couponRepository: CouponRepository,
    private val orderRepository: OrderRepository
) {

    fun execute(orderInputDto: OrderInputDto): OrderOutputDto {
        val order = Order(orderInputDto.cpf)

        val distance = zipcodeCalculatorAPIPort.calculate("11111111", orderInputDto.zipcode)

        orderInputDto.items.map { inputItem ->

            itemRepository.getById(inputItem.id)
                ?.let { itemFound ->
                    order.addItem(itemFound.id, itemFound.price, inputItem.quantity)
                    order.shippingPrice += shippingCalculator.calculate(distance, itemFound) * inputItem.quantity
                }
                ?: run { throw Error("Item not found") }
        }

        couponRepository.findByName(orderInputDto.coupon)
            ?.let { couponFound ->
                order.addCoupon(couponFound)
            }

        orderRepository.save(order)

        return OrderOutputDto(order.getTotal(), order.shippingPrice)
    }

}