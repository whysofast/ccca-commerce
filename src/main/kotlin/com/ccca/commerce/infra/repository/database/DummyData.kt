package com.ccca.commerce.infra.repository.database

import com.ccca.commerce.domain.entity.Item
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DummyData(
    private val itemJpaRepository: ItemJpaRepository
) {

    @PostConstruct
    fun createDummyItemsInDatabase() {

        val items = listOf(
            Item("1", "Mouse", 100, 50, 50, 50, 22),
            Item("2", "Teclado", 200, 50, 50, 50, 22),
            Item("3", "Monitor", 500, 50, 50, 50, 22),
            Item("4", "Clips", 5, 1, 1, 1, 1)
        )

        items.forEach { item ->
            print("saving item $item \n")
            itemJpaRepository.save(item.toDbo())
        }


    }


}