package com.ccca.commerce.infra.repository.database

import com.ccca.commerce.domain.entity.TaxTable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "taxTable")
class TaxTableDBO(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val itemId: Long,
    val type: String,
    val value: Double
) {
    fun toModel() = TaxTable(
        itemId = itemId.toString(),
        type = type,
        value = value
    )
}

fun TaxTable.toDBO() = TaxTableDBO(
    itemId = itemId.toLong(),
    type = type,
    value = value
)