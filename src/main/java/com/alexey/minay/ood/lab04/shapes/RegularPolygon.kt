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

    private val mAngleBetweenVertex = (360.0 / vertexCount).toRad()

    override fun draw(canvas: ICanvas) {
        canvas.setColor(color)
        var lastPoint = Point(center.x + radius, center.y)
        var angleBetweenRadiusAndX = 0.0
        for (i in 0 until vertexCount) {
            val nextPoint = getNextPoint(lastPoint, angleBetweenRadiusAndX)
            canvas.drawLine(lastPoint, nextPoint)
            lastPoint = nextPoint
            angleBetweenRadiusAndX += mAngleBetweenVertex
        }
    }

    private fun getNextPoint(lastPoint: Point, angleBetweenRadiusAndX: Double): Point {
        val x = radius * cos(angleBetweenRadiusAndX) + lastPoint.x
        val y = radius * sin(angleBetweenRadiusAndX) + lastPoint.y
        return Point(x, y)
    }

    private fun Double.toRad() = this * PI / 180

}
