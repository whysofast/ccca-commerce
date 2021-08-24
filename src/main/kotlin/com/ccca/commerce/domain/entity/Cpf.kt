package com.ccca.commerce.domain.entity

class Cpf private constructor(
    val digits: String
) {
    companion object {
        operator fun invoke(digits: String): Cpf {

            if (!digits.isValid()) throw Exception("Invalid CPF")

            return Cpf(digits)
        }
    }
}

private fun String.isValid(): Boolean {

    fun String.hasInvalidLength(): Boolean {
        return this.length != 11
    }

    fun String.hasRepeatableDigits(): Boolean {
        return this.all { it == this.first() }
    }

    fun String.calculateDigit(factor: Int, max: Int): Int {
        val cpfAsDigitArrayLimited = this.map { it.digitToInt() }.slice(0 until max)
        var total = 0
        var fator = factor

        for (digit in cpfAsDigitArrayLimited) {
            total += digit * fator--
        }

        return if (total % 11 < 2) 0 else (11 - total % 11)

    }

    val cleanCPF = Regex("""\D""").replace(this, "")

    if (cleanCPF.isBlank()) return false
    if (cleanCPF.hasInvalidLength()) return false
    if (cleanCPF.hasRepeatableDigits()) return false

    val firstDigit = cleanCPF.calculateDigit(10, 9)
    val secondDigit = cleanCPF.calculateDigit(11, 10)

    val lastTwoDigits = cleanCPF.slice(9 until 11)

    return lastTwoDigits == "${firstDigit}${secondDigit}"
}