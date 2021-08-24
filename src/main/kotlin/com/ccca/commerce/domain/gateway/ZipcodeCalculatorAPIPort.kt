package com.ccca.commerce.domain.gateway

interface ZipcodeCalculatorAPIPort {
    fun calculate(zipcodeFrom: String, zipcodeTo: String): Int
}