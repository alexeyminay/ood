package com.alexey.minay.ood.lab09.domain.shapes

import com.alexey.minay.ood.lab09.domain.Point

data class Frame(
        var leftTop: Point,
        var rightBottom: Point
) {

    val center: Point
        get() = Point(
                x = (rightBottom.x - leftBottom.x) / 2 + leftBottom.x,
                y = (leftBottom.y - leftTop.y) / 2 + leftTop.y
        )

    val rightTop: Point
        get() = Point(
                y = leftTop.y,
                x = rightBottom.x
        )

    val leftBottom: Point
        get() = Point(
                x = leftTop.x,
                y = rightBottom.y
        )

    fun copy() = Frame(
            leftTop.copy(),
            rightBottom.copy()
    )

}