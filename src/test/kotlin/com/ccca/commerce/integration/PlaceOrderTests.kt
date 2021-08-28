package com.ccca.commerce.integration

import com.ccca.commerce.application.PlaceOrderInputDto
import com.ccca.commerce.application.PlaceOrderUseCase
import com.ccca.commerce.domain.entity.Cpf
import com.ccca.commerce.domain.entity.OrderItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate


@SpringBootTest
class PlaceOrderTests {

    @Autowired
    private lateinit var placeOrderUseCase: PlaceOrderUseCase

    @Test
    fun `should place an order `() {

        val orderInputDto = PlaceOrderInputDto(
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

        val orderInputDto = PlaceOrderInputDto(
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

        val orderInputDto = PlaceOrderInputDto(
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

        val orderInputDto = PlaceOrderInputDto(
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
    fun `should place an order calculating code`() {

        val orderInputDto = PlaceOrderInputDto(
            cpf = Cpf("01234567890"),
            zipcode = "45000000",
            items = listOf(
                OrderItem("4", 100, 1),
            ),
            issueDate = LocalDate.of(2021, 1, 1),
            coupon = "VALE20EXPIRADO"
        )

        val orderInputDto2 = PlaceOrderInputDto(
            cpf = Cpf("01234567890"),
            zipcode = "45000000",
            items = listOf(
                OrderItem("3", 100, 1),
            ),
            issueDate = LocalDate.of(2021, 1, 1),
            coupon = "VALE20EXPIRADO"
        )

        placeOrderUseCase.execute(orderInputDto2)
        placeOrderUseCase.execute(orderInputDto2)
        val orderOutputDto = placeOrderUseCase.execute(orderInputDto)

        assertEquals(orderOutputDto.code.value, "202100000003")
    }


    @Test
    fun `should not create order with unknown item`() {
        val orderInputDto = PlaceOrderInputDto(
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
