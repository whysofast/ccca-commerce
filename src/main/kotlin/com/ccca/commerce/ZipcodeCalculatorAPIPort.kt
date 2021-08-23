package com.ccca.commerce

interface ZipcodeCalculatorAPIPort {
    fun calculate(zipcodeFrom: String, zipcodeTo: String): Int
}