package com.ccca.commerce.unit

import com.ccca.commerce.Coupon
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class CouponTest {

    @Test
    fun `should verify is coupon is expired`() {
        val coupon = Coupon("VALE20EXPIRADO", 20.0, LocalDateTime.of(2020, 1, 1, 0, 0, 0))

        assertEquals(coupon.isNotExpired(), false)
    }
}