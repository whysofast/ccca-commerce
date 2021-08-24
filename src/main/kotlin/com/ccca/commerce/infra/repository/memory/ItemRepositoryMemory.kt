package com.ccca.commerce.infra.repository.memory

import com.ccca.commerce.domain.entity.Item
import com.ccca.commerce.domain.repository.ItemRepository
import org.springframework.stereotype.Component

@Component
class ItemRepositoryMemory : ItemRepository {

    private val items = listOf(
        Item("1", "Mouse", 100, 50, 50, 50, 22),
        Item("2", "Teclado", 200, 50, 50, 50, 22),
        Item("3", "Monitor", 500, 50, 50, 50, 22),
        Item("4", "Clips", 5, 1, 1, 1, 1)
    )

    override fun getById(id: String): Item? {
        return this.items.find { item -> item.id == id }
    }

}