package com.ccca.commerce.application.getOrder

interface GetOrderPort {
    fun execute(code: String): GetOrderOutput
}