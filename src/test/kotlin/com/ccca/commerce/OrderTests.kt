package com.ccca.commerce

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OrderTests {

    @Test
    fun `should not create order with Invalid CPF`() {
        val cpf = "111.111.111-11"

        val newOrder = Order(cpf)

        assertEquals(teste,1)
    }

}
