package com.ccca.commerce.infra.gateway.memory

import com.ccca.commerce.domain.gateway.ZipcodeCalculatorAPIPort
import org.springframework.stereotype.Component

@Component
class ZipcodeCalculatorAPI() : ZipcodeCalculatorAPIPort {
    override fun calculate(zipcodeFrom: String, zipcodeTo: String): Int {
        return 1000
    }
}