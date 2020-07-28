package com.alexey.minay.ood.lab04.shapes

class Ellipse(
        override val color: Color,
        val center: Int,
        val horizontalRadius: Int,
        val verticalRadius: Int
) : Shape(color) {

    override fun draw(canvas: ICanvas) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}