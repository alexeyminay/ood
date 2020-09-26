package com.alexey.minay.ood.lab04.shapes

import com.alexey.minay.ood.lab04.ICanvas

class Triangle(
        override val color: Color,
        val vertex1: Point,
        val vertex2: Point,
        val vertex3: Point
) : Shape(color) {

    override fun draw(canvas: ICanvas) {
        canvas.setColor(color)
        canvas.drawLine(vertex1, vertex2)
        canvas.drawLine(vertex2, vertex3)
        canvas.drawLine(vertex3, vertex1)
    }

}