package com.ccca.commerce.integration

import com.ccca.commerce.domain.entity.Item
import com.ccca.commerce.domain.factory.TaxCalculatorFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class TaxCalculatorTest() {

    @Test
    fun `should calculate a Guitarra item tax for any month but november`() {
        val item = Item("1", "Guitarra", 1000, 100, 50, 30, 8)

        val taxCalculator = TaxCalculatorFactory().create(LocalDate.of(2021, 5, 10))

        val taxAmount = taxCalculator.calculate(item)

        assertEquals(150.0, taxAmount)
    }

    @Test
    fun `should calculate a Guitarra item tax for november`() {
        val item = Item("1", "Guitarra", 1000, 100, 50, 30, 8)

        val taxCalculator = TaxCalculatorFactory().create(LocalDate.of(2021, 11, 10))

        val taxAmount = taxCalculator.calculate(item)

        assertEquals(50.0, taxAmount)
    }

    @Test
    fun `should calculate a Cabo item tax for any month but november`() {
        val item = Item("2", "Cabo", 30, 10, 10, 10, 1)

        val taxCalculator = TaxCalculatorFactory().create(LocalDate.of(2021, 5, 10))

        val taxAmount = taxCalculator.calculate(item)

        assertEquals(1.5, taxAmount)
    }

    @Test
    fun `should calculate a Cabo item tax for november`() {
        val item = Item("2", "Cabo", 30, 10, 10, 10, 1)

        val taxCalculator = TaxCalculatorFactory().create(LocalDate.of(2021, 11, 10))

        val taxAmount = taxCalculator.calculate(item)

        assertEquals(0.3, taxAmount)
    }

}