package com.ccca.commerce

interface OrderRepository {
    fun save(order: Order): Order
}