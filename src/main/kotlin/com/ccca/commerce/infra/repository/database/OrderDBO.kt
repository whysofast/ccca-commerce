package com.ccca.commerce.infra.repository.database

import com.ccca.commerce.domain.entity.Cpf
import com.ccca.commerce.domain.entity.Order
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity(name = "pedido")
class OrderDBO(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    val cpf: String,

    @ManyToOne
    @JoinColumn(name = "name")
    val coupon: CouponDBO? = null,

    val shippingPrice: Double = 0.0,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    val items: MutableList<OrderItemDBO> = mutableListOf(),

    val issueDate: LocalDate = LocalDate.now(),

    val sequence: Int = 1,

    val code: String,

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "modified_at")
    var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toModel() = Order(
        cpf = Cpf(cpf),
        coupon = coupon?.toModel(),
        items = items.map { it.toModel() }.toMutableList(),
        shippingPrice = shippingPrice,
        issueDate = issueDate,
        sequence = sequence
    )
}

fun Order.toDBO(coupon: CouponDBO? = null): OrderDBO {

    val orderDBO = OrderDBO(
        cpf = cpf.digits,
        coupon = coupon,
        shippingPrice = shippingPrice,
        issueDate = issueDate,
        sequence = sequence,
        code = code.value
    )
    items.map { orderDBO.items.add(it.toDBO()) }
    return orderDBO
}
