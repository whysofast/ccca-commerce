package com.ccca.commerce.unit

import com.ccca.commerce.Coupon
import com.ccca.commerce.Cpf
import com.ccca.commerce.Order
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime


@SpringBootTest
class OrderTests {

    @Test
    fun `should not create order with Invalid CPF`() {
        val cpf = "111.111.111-11"

        val exception: Throwable = assertThrows(Exception::class.java) { Order(Cpf(cpf)) }

        assertEquals("Invalid CPF", exception.message)

    }

    @Test
    fun `should create a order with 3 products`() {
        val cpf = "01234567890"

        val order = Order(Cpf(cpf))
        order.addItem("1", 100, 1)
        order.addItem("2", 200, 2)
        order.addItem("3", 500, 3)

        val total = order.getTotal()
        assertEquals(total, 2000)
    }

    @Test
    fun `should create a order with a discount coupon`() {
        val cpf = "01234567890"

        val order = Order(Cpf(cpf))
        order.addItem("1", 100, 1)
        order.addItem("2", 200, 2)
        order.addItem("3", 500, 3)

        order.addCoupon(Coupon("FAST20", 20.0, LocalDateTime.of(2022, 1, 1, 0, 0, 0)))

        val total = order.getTotal()
        assertEquals(total, 1600)
    }

    @Test
    fun `should create a order with a coupon expired`() {
        val cpf = "01234567890"

        val order = Order(Cpf(cpf))
        order.addItem("1", 100, 1)
        order.addItem("2", 200, 2)
        order.addItem("3", 500, 3)

        order.addCoupon(Coupon("FAST20", 20.0, LocalDateTime.of(2020, 1, 1, 0, 0, 0)))

        val total = order.getTotal()
        assertEquals(total, 2000)
    }
}
