package com.ccca.commerce.domain.service

import com.ccca.commerce.domain.entity.Item
import org.springframework.stereotype.Component

@Component
class ShippingCalculator {

    fun calculate(distance: Int, item: Item): Double {
        var price = distance * item.getVolume() * item.getDensity() / 100
        if (price < 10.0) price = 10.0
        return price
    }
}