package com.ccca.commerce

interface ShippingCalculatorPort {
    fun calculate(distance: Int, item: Item): Double
}