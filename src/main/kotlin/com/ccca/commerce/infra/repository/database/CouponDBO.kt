package com.ccca.commerce.infra.repository.database

import com.ccca.commerce.domain.entity.Coupon
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "coupon")
class CouponDBO(
    @Id
    val name: String,
    val discount: Double,
    val expiresAt: LocalDateTime,
    @CreatedDate
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    @Column(name = "modified_at")
    var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toModel() = Coupon(
        name = name,
        discount = discount,
        expiresAt = expiresAt
    )
}

fun Coupon.toDbo() = CouponDBO(
    name = name,
    discount = discount,
    expiresAt = expiresAt
)