package com.ccca.commerce

import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CouponRepositoryMemory : CouponRepository {

    private val coupons = listOf(
        Coupon("VALE20", 20.0, LocalDateTime.of(2022, 1, 1, 0, 0, 0)),
        Coupon("VALE20EXPIRADO", 20.0, LocalDateTime.of(2020, 1, 1, 0, 0, 0))
    )

    override fun findByName(name: String): Coupon? {
        return this.coupons.find { coupon -> name == coupon.name }
    }
}