package com.alexey.minay.ood.lab04.shapes

class Rectangle(
        override val color: Color,
        val leftTop: Int,
        val rightBottom: Int
) : Shape(color) {

    override fun draw(canvas: ICanvas) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}