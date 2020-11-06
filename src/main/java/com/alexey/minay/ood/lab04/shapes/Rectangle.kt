package com.alexey.minay.ood.lab04.shapes

import com.alexey.minay.ood.lab04.ICanvas

class Rectangle(
        override val color: Color,
        val leftTop: Point,
        val rightBottom: Point
) : Shape(color) {

    private val mLeftBottom = Point(leftTop.x, rightBottom.y)
    private val mRightTop = Point(rightBottom.x, leftTop.y)

    override fun draw(canvas: ICanvas) {
        canvas.setColor(color)
        canvas.drawLine(leftTop, mLeftBottom)
        canvas.drawLine(mLeftBottom, rightBottom)
        canvas.drawLine(rightBottom, mRightTop)
        canvas.drawLine(mRightTop, leftTop)
    }
}