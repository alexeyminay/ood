package com.alexey.minay.ood.lab09.application.common

data class AppFrame(
    var leftTop: AppPoint,
    var rightBottom: AppPoint
) {

    val width: Double
        get() = rightBottom.x - leftTop.x

    val height: Double
        get() = rightBottom.y - leftTop.y

    val center: AppPoint
        get() = AppPoint(
            x = (rightBottom.x - leftBottom.x) / 2 + leftBottom.x,
            y = (leftBottom.y - leftTop.y) / 2 + leftTop.y
        )

    val rightTop: AppPoint
        get() = AppPoint(
            y = leftTop.y,
            x = rightBottom.x
        )

    val leftBottom: AppPoint
        get() = AppPoint(
            x = leftTop.x,
            y = rightBottom.y
        )

    fun copy() = AppFrame(
        leftTop.copy(),
        rightBottom.copy()
    )

}