package com.ccca.commerce.domain.service

import com.ccca.commerce.domain.entity.Item

class DefaultTaxCalculator : TaxCalculator() {
    override fun getTax(item: Item): Double {

        if (item.description == "Guitarra") {
            return 15.0
        }

        if (item.description == "Cabo") {
            return 5.0
        }

        return 0.0
    }
}