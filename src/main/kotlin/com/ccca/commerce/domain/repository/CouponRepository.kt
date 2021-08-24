package com.ccca.commerce.domain.repository

import com.ccca.commerce.domain.entity.Coupon

interface CouponRepository {
    fun findByName(name: String): Coupon?
}