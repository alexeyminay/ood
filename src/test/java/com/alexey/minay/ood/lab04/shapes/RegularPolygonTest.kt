package com.alexey.minay.ood.lab04.shapes

import com.alexey.minay.ood.lab04.ICanvas
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin

class RegularPolygonTest {

    private var mPolygon: RegularPolygon? = null
    private val mCanvas = mock<ICanvas>()

    @Test
    fun shouldDrawRectangle() {
        val center = Point(40.0, 40.0)
        val vertexCount = 4
        val radius = 20
        val color = Color.BLACK
        mPolygon = RegularPolygon(
                color = color,
                vertexCount = vertexCount,
                center = center,
                radius = radius
        )
        mPolygon?.draw(mCanvas)
        val vertex1 = Point(center.x + radius, center.y)
        val vertex2 = calculateVertex(1, vertexCount, center, radius)
        val vertex3 = calculateVertex(2, vertexCount, center, radius)
        val vertex4 = calculateVertex(3, vertexCount, center, radius)
        val vertex5 = calculateVertex(4, vertexCount, center, radius)
        verify(mCanvas).setColor(color)
        verify(mCanvas).drawLine(vertex1, vertex2)
        verify(mCanvas).drawLine(vertex2, vertex3)
        verify(mCanvas).drawLine(vertex3, vertex4)
        verify(mCanvas).drawLine(vertex4, vertex1)
    }

    private fun calculateVertex(vertexNum: Int, vertexCount: Int, center: Point, radius: Int): Point {
        val x = center.x + radius * cos((2 * PI * vertexNum) / vertexCount)
        val y = center.y + radius * sin((2 * PI * vertexNum) / vertexCount)
        return Point(round(x), round(y))
    }

}