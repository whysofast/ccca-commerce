package com.ccca.commerce

import org.springframework.stereotype.Component

@Component
class ZipcodeCalculatorAPI() : ZipcodeCalculatorAPIPort {
    override fun calculate(zipcodeFrom: String, zipcodeTo: String): Int {
        return 1000
    }
}