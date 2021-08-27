package com.ccca.commerce.application

import com.ccca.commerce.domain.entity.Order
import com.ccca.commerce.domain.gateway.ZipcodeCalculatorAPIPort
import com.ccca.commerce.domain.repository.CouponRepository
import com.ccca.commerce.domain.repository.ItemRepository
import com.ccca.commerce.domain.repository.OrderRepository
import com.ccca.commerce.domain.service.ShippingCalculator
import org.springframework.stereotype.Component

@Component
class PlaceOrderUseCase(
    private val shippingCalculator: ShippingCalculator,
    private val zipcodeCalculatorAPIPort: ZipcodeCalculatorAPIPort,
    private val itemRepository: ItemRepository,
    private val couponRepository: CouponRepository,
    private val orderRepository: OrderRepository
) {

    fun execute(placeOrderInputDto: PlaceOrderInputDto): PlaceOrderOutputDto {

        val sequence = orderRepository.count() + 1

        val order = Order(placeOrderInputDto.cpf, issueDate = placeOrderInputDto.issueDate, sequence = sequence)

        val distance = zipcodeCalculatorAPIPort.calculate("11111111", placeOrderInputDto.zipcode)

        placeOrderInputDto.items.map { inputItem ->

            itemRepository.getById(inputItem.id)
                ?.let { itemFound ->
                    order.addItem(itemFound.id, itemFound.price, inputItem.quantity)
                    order.shippingPrice += shippingCalculator.calculate(distance, itemFound) * inputItem.quantity
                }
                ?: run { throw Error("Item not found") }
        }

        couponRepository.findByName(placeOrderInputDto.coupon)
            ?.let { couponFound ->
                order.addCoupon(couponFound)
            }

        orderRepository.save(order)

        return PlaceOrderOutputDto(order.getTotal(), order.shippingPrice, order.code)
    }

}