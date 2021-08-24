package com.ccca.commerce.integration

import com.ccca.commerce.Item
import com.ccca.commerce.ShippingCalculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ShippingCalculatorTest(

    @Autowired
    private val shippingCalculator: ShippingCalculator
) {

    @Test
    fun `should calculate shipping amount correctly`() {
        val item = Item("1", "Mouse", 100, 50, 50, 50, 22)

        val distance = 1000

        val shippingPrice = shippingCalculator.calculate(distance, item)

        assertEquals(220.0, shippingPrice)
    }
}