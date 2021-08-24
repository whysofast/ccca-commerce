package com.ccca.commerce.domain.repository

import com.ccca.commerce.domain.entity.Item

// (Port) -> Adapter -> Repository <= JpaRepository
interface ItemRepository {
    fun getById(id: String): Item?
}
