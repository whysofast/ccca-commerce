package com.ccca.commerce.domain.repository.port

import com.ccca.commerce.domain.entity.TaxTable

interface TaxTableRepository {
    fun getByItemId(id: String): List<TaxTable?>
}
