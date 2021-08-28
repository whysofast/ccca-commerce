package com.ccca.commerce.domain.entity

import java.time.Duration
import java.time.LocalDateTime

data class Coupon(
    val name: String,
    val discount: Double,
    val expiresAt: LocalDateTime
) {
    fun isNotExpired() = Duration.between(this.expiresAt, LocalDateTime.now()).toDays() < 0
}