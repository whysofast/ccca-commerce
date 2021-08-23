package com.ccca.commerce

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ItemTest {

    @Test
    fun `should calculate item volume`() {
        val item = Item("1", "Mouse", 100, 50,50,50,22)

        val volume = item.getVolume()

        assertEquals(0.0125,volume)
    }

    @Test
    fun `should calculate item density`() {
        val item = Item("1", "Mouse", 100, 50,50,50,22)

        val density = item.getDensity()

        assertEquals(1760.0,density)
    }
}