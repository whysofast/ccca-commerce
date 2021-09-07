package com.ccca.commerce.infra.repository.database

import com.ccca.commerce.domain.entity.OrderItem
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "orderItem")
class OrderItemDBO(

    @Id
    val id: String,

    val price: Long,

    val quantity: Long = 1
) {
    fun toModel() = OrderItem(
        id = id,
        price = price,
        quantity = quantity
    )
}

fun OrderItem.toDbo() = OrderItemDBO(
    id = id,
    price = price,
    quantity = quantity
)