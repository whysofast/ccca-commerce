package com.ccca.commerce.domain.entity

import java.time.LocalDate

data class Order(
    val cpf: Cpf,
    private var coupon: Coupon? = null,
    val items: MutableList<OrderItem> = mutableListOf(),
    var shippingPrice: Double = 0.0,
    val issueDate: LocalDate = LocalDate.now(),
    val sequence: Int = 1,
    var code: OrderCode = OrderCode(issueDate, sequence)
) {

    fun addItem(
        id: String,
        price: Long,
        quantity: Long
    ) {
        items.add(OrderItem(id, price, quantity))
    }

    fun addCoupon(coupon: Coupon) {
        if (coupon.isNotExpired()) {
            this.coupon = coupon
        }
    }

    fun getTotal(): Long {
        var total = 0L
        this.items.map { total += it.price * it.quantity }

        this.coupon?.let { total -= (total * it.discount / 100).toLong() }

        total += this.shippingPrice.toLong()

        return total
    }
}