package com.ccca.commerce.domain.factory

import com.ccca.commerce.domain.service.DefaultTaxCalculator
import com.ccca.commerce.domain.service.NovemberTaxCalculator
import com.ccca.commerce.domain.service.TaxCalculator
import java.time.LocalDate


private const val NOVEMBER = 11

class TaxCalculatorFactory {

    fun create(date: LocalDate): TaxCalculator {
        if (date.month.value == NOVEMBER) {
            return NovemberTaxCalculator()
        }
        return DefaultTaxCalculator()

    }
}