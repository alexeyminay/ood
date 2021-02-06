package com.alexey.minay.ood.lab09.domain.shapes

data class Frame(
    val leftTop: Point,
    val rightBottom: Point
) {
    fun copy() = Frame(leftTop.copy(), rightBottom.copy())
}