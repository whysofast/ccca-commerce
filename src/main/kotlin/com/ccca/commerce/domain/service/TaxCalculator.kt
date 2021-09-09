package com.ccca.commerce.domain.service

import com.ccca.commerce.domain.entity.Item
import org.springframework.stereotype.Component

@Component
abstract class TaxCalculator {

    fun calculate(item: Item): Double {

        return ((item.price * getTax(item)) / 100)

    }

    abstract fun getTax(item: Item): Double
}