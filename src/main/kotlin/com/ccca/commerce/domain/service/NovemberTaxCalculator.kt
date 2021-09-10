package com.ccca.commerce.domain.service

import com.ccca.commerce.domain.entity.TaxTable

class NovemberTaxCalculator : TaxCalculator() {
    override fun getTax(taxTables: List<TaxTable?>): Double {

        taxTables.find { taxTable -> taxTable?.type == "november" }
            ?.let { return it.value }
            ?: run { return 0.0 }
    }
}