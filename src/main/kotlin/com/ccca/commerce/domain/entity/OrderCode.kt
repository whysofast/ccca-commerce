package com.ccca.commerce.domain.entity

import java.time.LocalDate

data class OrderCode(
    val issueDate: LocalDate,
    val sequence: Int
) {
    val value = "${issueDate.year}${sequence.toString().padStart(8, '0')}"
}
