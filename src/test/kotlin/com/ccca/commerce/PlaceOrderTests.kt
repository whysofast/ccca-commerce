package com.ccca.commerce

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class PlaceOrderTests {

    @Autowired
    private lateinit var placeOrderUseCase: PlaceOrderUseCase

    @Test
    fun `should place an order `() {

        val orderInputDto = OrderInputDto(
            cpf = Cpf("01234567890"),
            items = listOf(
                OrderItem("Mouse", 100, 1),
                OrderItem("Teclado", 200, 2),
                OrderItem("Monitor", 500, 3)
            ),
            coupon = "VALE20"
        )

        val orderOutputDto = placeOrderUseCase.execute(orderInputDto)

        assertEquals(orderOutputDto.total, 1600)
    }
}
