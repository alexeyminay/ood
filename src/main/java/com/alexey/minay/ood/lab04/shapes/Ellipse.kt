package com.alexey.minay.ood.lab04.shapes

import com.alexey.minay.ood.lab04.ICanvas

class Ellipse(
        override val color: Color,
        val center: Point,
        val horizontalRadius: Int,
        val verticalRadius: Int
) : Shape(color) {

    override fun draw(canvas: ICanvas) {
        canvas.setColor(color)
        canvas.drawEllipse(center, horizontalRadius, verticalRadius)
    }

}