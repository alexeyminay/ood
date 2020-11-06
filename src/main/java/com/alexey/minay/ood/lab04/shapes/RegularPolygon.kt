package com.alexey.minay.ood.lab04.shapes

import com.alexey.minay.ood.lab04.ICanvas
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class RegularPolygon(
        override val color: Color,
        val vertexCount: Int,
        val center: Point,
        val radius: Int
) : Shape(color) {

    override fun draw(canvas: ICanvas) {
        canvas.setColor(color)
        var fromVertex = Point(center.x + radius, center.y)
        for (i in 0..vertexCount) {
            val x = center.x + radius * cos((2 * PI * i) / vertexCount)
            val y = center.y + radius * sin((2 * PI * i) / vertexCount)
            val toVertex = Point(x, y)
            canvas.drawLine(fromVertex, toVertex)
            fromVertex = toVertex
        }
    }

}
