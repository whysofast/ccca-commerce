package com.ccca.commerce.domain.entity

class Item(
    val id: String,
    val description: String,
    val price: Long,
    val width: Int,
    val height: Int,
    val length: Int,
    val weight: Int
) {
    fun getVolume() = width * height * length / 10e6

    fun getDensity() = weight / getVolume()
}