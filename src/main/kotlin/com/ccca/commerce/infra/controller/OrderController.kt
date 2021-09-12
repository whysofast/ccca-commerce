package com.ccca.commerce.infra.controller

import com.ccca.commerce.application.getOrder.GetOrderOutput
import com.ccca.commerce.application.getOrder.GetOrderPort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val getOrder: GetOrderPort
) {

    @GetMapping("orders/{code}")
    fun getOrderCode(
        @PathVariable("code") code: String
    ): GetOrderOutput {

        return getOrder.execute(code)
    }
}