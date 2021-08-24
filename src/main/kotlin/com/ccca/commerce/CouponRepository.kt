package com.ccca.commerce

interface CouponRepository {
    fun findByName(name: String): Coupon?
}