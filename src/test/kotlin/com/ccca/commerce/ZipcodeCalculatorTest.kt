package com.ccca.commerce

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ZipcodeCalculatorTest(

    @Autowired
    private val zipcodeCalculatorApi: ZipcodeCalculatorAPI

) {

    @Test
    fun `should calculate distance between two zipcodes`() {
        val distance = zipcodeCalculatorApi.calculate("11111111", "12345678")

        assertEquals(1000, distance)
    }
}