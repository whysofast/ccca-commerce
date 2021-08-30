package com.ccca.commerce.infra.repository.database

import com.ccca.commerce.domain.entity.Coupon
import com.ccca.commerce.domain.entity.Item
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.annotation.PostConstruct

@Component
class DummyData(
    private val itemJpaRepository: ItemJpaRepository,
    private val couponJpaRepository: CouponJpaRepository
) {

    @PostConstruct
    fun createDummyItemsInMemoryDatabase() {

        val items = listOf(
            Item("1", "Mouse", 100, 50, 50, 50, 22),
            Item("2", "Teclado", 200, 50, 50, 50, 22),
            Item("3", "Monitor", 500, 50, 50, 50, 22),
            Item("4", "Clips", 5, 1, 1, 1, 1)
        )

        items.forEach { item ->
            print("saving item $item \n")
            itemJpaRepository.save(item.toDbo())
        }

        val coupons = listOf(
            Coupon("VALE20", 20.0, LocalDateTime.of(2022, 1, 1, 0, 0, 0)),
            Coupon("VALE20EXPIRADO", 20.0, LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        )

        coupons.forEach { coupon ->
            print("saving coupon $coupon")
            couponJpaRepository.save(coupon.toDbo())
        }
    }


}