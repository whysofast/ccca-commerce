package com.ccca.commerce

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
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
            zipcode = "45000000",
            items = listOf(
                OrderItem("1", 100, 1),
                OrderItem("2", 200, 2),
                OrderItem("3", 500, 3)
            ),
            coupon = "VALE20"
        )

        val orderOutputDto = placeOrderUseCase.execute(orderInputDto)

        assertEquals(orderOutputDto.total, 2920)
    }

    @Test
    fun `should place an order with expired coupon`() {

        val orderInputDto = OrderInputDto(
            cpf = Cpf("01234567890"),
            zipcode = "45000000",
            items = listOf(
                OrderItem("1", 100, 1),
                OrderItem("2", 200, 2),
                OrderItem("3", 500, 3)
            ),
            coupon = "VALE20EXPIRADO"
        )

        val orderOutputDto = placeOrderUseCase.execute(orderInputDto)

        assertEquals(orderOutputDto.total, 3320)
    }

    @Test
    fun `should place an order with shipping price calculated correctly`() {

        val orderInputDto = OrderInputDto(
            cpf = Cpf("01234567890"),
            zipcode = "45000000",
            items = listOf(
                OrderItem("1", 100, 1),
                OrderItem("2", 200, 2),
                OrderItem("3", 500, 3)
            ),
            coupon = "VALE20EXPIRADO"
        )

        val orderOutputDto = placeOrderUseCase.execute(orderInputDto)

        assertEquals(orderOutputDto.shippingPrice, 1320.0)
    }

    @Test
    fun `should place an order with MINIMUN shipping price calculated correctly`() {

        val orderInputDto = OrderInputDto(
            cpf = Cpf("01234567890"),
            zipcode = "45000000",
            items = listOf(
                OrderItem("4", 100, 1),
            ),
            coupon = "VALE20EXPIRADO"
        )

        val orderOutputDto = placeOrderUseCase.execute(orderInputDto)

        assertEquals(orderOutputDto.shippingPrice, 10.0)
    }


    @Test
    fun `should not create order with unknown item`() {
        val orderInputDto = OrderInputDto(
            cpf = Cpf("01234567890"),
            zipcode = "45000000",
            items = listOf(
                OrderItem("123412341234", 100, 1),
            ),
            coupon = "VALE20EXPIRADO"
        )

        val error: Throwable = Assertions.assertThrows(Error::class.java) { placeOrderUseCase.execute(orderInputDto) }

        assertEquals("Item not found", error.message)
    }
}
