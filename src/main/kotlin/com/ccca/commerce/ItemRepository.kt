package com.ccca.commerce

//@Repository
interface ItemRepository {
    fun getById(id: String): Item?
}