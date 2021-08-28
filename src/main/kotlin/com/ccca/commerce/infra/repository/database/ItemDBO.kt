package com.ccca.commerce.infra.repository.database

import com.ccca.commerce.domain.entity.Item
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "item")
class ItemDBO(
    @Id()
    val id: Long,
    val description: String,
    val price: Long,
    val width: Int,
    val height: Int,
    val length: Int,
    val weight: Int,
    @CreatedDate
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    @Column(name = "modified_at")
    var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toModel() = Item(
        id = id.toString(),
        description = description,
        price = price,
        width = width,
        height = height,
        length = length,
        weight = weight
    )
}

fun Item.toDbo() = ItemDBO(
    id = id.toLong(),
    description = description,
    price = price,
    width = width,
    height = height,
    length = length,
    weight = weight
)