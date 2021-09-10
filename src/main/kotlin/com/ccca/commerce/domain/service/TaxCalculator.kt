package com.ccca.commerce.domain.service

import com.ccca.commerce.domain.entity.OrderItem
import com.ccca.commerce.domain.entity.TaxTable
import org.springframework.stereotype.Component

@Component
abstract class TaxCalculator {
    
    fun calculate(orderItem: OrderItem, taxTables: List<TaxTable?>): Double {

        return ((orderItem.price * getTax(taxTables)) / 100)

    }

    abstract fun getTax(taxTables: List<TaxTable?>): Double
}