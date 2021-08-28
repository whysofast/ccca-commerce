package com.ccca.commerce.unit

import com.ccca.commerce.domain.entity.Item
import com.ccca.commerce.infra.repository.database.ItemJpaRepository
import com.ccca.commerce.infra.repository.database.toDbo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.Math.random

@SpringBootTest
class JpaH2Tests {

    @Autowired
    private lateinit var itemJpaRepository: ItemJpaRepository

    @Test
    fun `should insert and read from database`() {

        val item = Item(random().toLong().toString(), "Mousinho", 100, 50, 50, 50, 22)
        val itemDBO = item.toDbo()

        itemJpaRepository.save(itemDBO)

        val foundItem = itemJpaRepository.findById(itemDBO.id).orElseGet { null }

        assertEquals(foundItem.id, item.id.toLong())
        assertEquals(foundItem.description, "Mousinho")
    }
}