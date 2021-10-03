package com.ccca.commerce.infra.controller

import com.ccca.commerce.application.PlaceOrderInputDto
import com.ccca.commerce.application.PlaceOrderInputRESTDto
import com.ccca.commerce.application.PlaceOrderOutputDto
import com.ccca.commerce.application.PlaceOrderUseCase
import com.ccca.commerce.application.getOrder.GetOrderOutput
import com.ccca.commerce.application.getOrder.GetOrderPort
import com.ccca.commerce.domain.entity.Cpf
import com.ccca.commerce.domain.entity.OrderItem
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class OrderController(
    private val getOrder: GetOrderPort,
    private val placeOrderUseCase: PlaceOrderUseCase
) {

    @GetMapping("orders/{code}")
    fun getOrderCode(
        @PathVariable("code") code: String
    ): GetOrderOutput {

        return getOrder.execute(code)
    }

    @PostMapping("orders")
    fun placeOrder(
        @Valid @RequestBody dto: PlaceOrderInputRESTDto
    ) : PlaceOrderOutputDto {

        return placeOrderUseCase.execute(dto.toDTO())
    }
}