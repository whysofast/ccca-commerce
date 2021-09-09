package com.ccca.commerce.domain.service

import com.ccca.commerce.domain.entity.Item

class NovemberTaxCalculator : TaxCalculator() {
    override fun getTax(item: Item): Double {

        if (item.description == "Guitarra") {
            return 5.0
        }

        if (item.description == "Cabo") {
            return 1.0
        }

        return 0.0
    }
}