package com.ccca.commerce.infra.repository.adapter.memory

import com.ccca.commerce.domain.entity.Coupon
import com.ccca.commerce.domain.repository.port.CouponRepository
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