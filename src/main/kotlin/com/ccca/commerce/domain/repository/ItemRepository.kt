package com.ccca.commerce.domain.repository

import com.ccca.commerce.domain.entity.Item

//@Repository
interface ItemRepository {
    fun getById(id: String): Item?
}