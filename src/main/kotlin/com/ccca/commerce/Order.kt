package com.ccca.commerce

class Order(
    val cpf: Cpf,
    var coupon: Coupon? = null,
    val items: MutableList<OrderItem> = mutableListOf(),
) {

    fun addItem(
        description : String,
        price: Long,
        quantity: Long
    ) {
        items.add(OrderItem(description,price,quantity))
    }

    fun addCoupon(coupon: Coupon ){
        this.coupon = coupon
    }

    fun getTotal() : Long {
        var total = 0L
        this.items.map { total += it.price * it.quantity }

        this.coupon?.let { total -= (total*it.discount/100).toLong() }

        return total
    }
}