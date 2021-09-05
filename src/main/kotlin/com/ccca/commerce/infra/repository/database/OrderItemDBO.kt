package com.ccca.commerce.infra.repository.database

import com.ccca.commerce.domain.entity.OrderItem
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class OrderItemDBO(

    @Id
    val id: String,

    val price: Long,

    val quantity: Long = 1
//    ,
//
//    @ManyToOne(optional = false)
//    @field:JoinColumn(name = "order_id")
//    val order: OrderDBO
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
    quantity = quantity,
)