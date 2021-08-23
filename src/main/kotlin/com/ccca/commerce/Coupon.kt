package com.ccca.commerce

import java.time.Duration
import java.time.LocalDateTime

class Coupon(
    val name: String,
    val discount: Double,
    val expiresAt : LocalDateTime
) {
    fun isNotExpired() = Duration.between(this.expiresAt, LocalDateTime.now()).toDays() < 0
}