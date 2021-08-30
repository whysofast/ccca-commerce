package com.ccca.commerce.infra.repository.adapter.memory

import com.ccca.commerce.domain.entity.Coupon
import com.ccca.commerce.domain.repository.port.CouponRepository
import com.ccca.commerce.infra.repository.database.CouponJpaRepository
import org.springframework.stereotype.Component

@Component
class CouponRepositoryMemory(
    private val couponJpaRepository: CouponJpaRepository
) : CouponRepository {


    override fun findByName(name: String): Coupon? {
        val couponFound = couponJpaRepository.findById(name).orElseGet { null }

        return couponFound?.toModel()
    }
}