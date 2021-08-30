package com.ccca.commerce.infra.repository.adapter.memory

import com.ccca.commerce.domain.entity.Item
import com.ccca.commerce.domain.repository.port.ItemRepository
import com.ccca.commerce.infra.repository.database.ItemJpaRepository
import org.springframework.stereotype.Component

// Port -> (Adapter) -> Repository <= JpaRepository
@Component
class ItemRepositoryMemory(
    private val itemJpaRepository: ItemJpaRepository
) : ItemRepository {

    override fun getById(id: String): Item? {
        val itemFound = itemJpaRepository.findById(id.toLong()).orElseGet { null }

        return itemFound?.toModel()
    }

}
