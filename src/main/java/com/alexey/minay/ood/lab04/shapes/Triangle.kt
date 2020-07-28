package com.alexey.minay.ood.lab04.shapes

class Triangle(
        override val color: Color,
        val vertex1: Int,
        val vertex2: Int,
        val vertex3: Int
) : Shape(color) {

    override fun draw(canvas: ICanvas) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}