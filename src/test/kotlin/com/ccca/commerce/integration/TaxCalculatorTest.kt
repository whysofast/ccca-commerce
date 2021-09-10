package com.ccca.commerce.integration

import com.ccca.commerce.domain.entity.OrderItem
import com.ccca.commerce.domain.entity.TaxTable
import com.ccca.commerce.domain.factory.TaxCalculatorFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class TaxCalculatorTest() {

    @Test
    fun `should calculate a Guitarra item tax for any month but november`() {
        val orderItem = OrderItem("1", 1000, 2)

        val taxTables = listOf(TaxTable("1", "default", 15.0), TaxTable("1", "november", 5.0))

        val taxCalculator = TaxCalculatorFactory().create(LocalDate.of(2021, 5, 10))

        val taxAmount = taxCalculator.calculate(orderItem, taxTables)

        assertEquals(150.0, taxAmount)
    }

    @Test
    fun `should calculate a Guitarra item tax for november`() {
        val orderItem = OrderItem("1", 1000, 2)

        val taxTables = listOf(TaxTable("1", "default", 15.0), TaxTable("1", "november", 5.0))

        val taxCalculator = TaxCalculatorFactory().create(LocalDate.of(2021, 11, 10))

        val taxAmount = taxCalculator.calculate(orderItem, taxTables)

        assertEquals(50.0, taxAmount)
    }

    @Test
    fun `should calculate a Cabo item tax for any month but november`() {
        val orderItem = OrderItem("2", 30, 2)

        val taxTables = listOf(TaxTable("3", "default", 5.0), TaxTable("2", "november", 1.0))

        val taxCalculator = TaxCalculatorFactory().create(LocalDate.of(2021, 5, 10))

        val taxAmount = taxCalculator.calculate(orderItem, taxTables)

        assertEquals(1.5, taxAmount)
    }

    @Test
    fun `should calculate a Cabo item tax for november`() {
        val orderItem = OrderItem("2", 30, 2)

        val taxTables = listOf(TaxTable("3", "default", 5.0), TaxTable("2", "november", 1.0))

        val taxCalculator = TaxCalculatorFactory().create(LocalDate.of(2021, 11, 10))

        val taxAmount = taxCalculator.calculate(orderItem, taxTables)

        assertEquals(0.3, taxAmount)
    }

}