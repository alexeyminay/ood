package com.alexey.minay.ood.lab04.shapes

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class TriangleTest {

    private var mTriangle: Triangle? = null
    private val mCanvas = mock<ICanvas>()

    @Test
    fun shouldDrawTriangle() {
        val color = Color.BLACK
        val vertex1 = Point(10.0, 20.0)
        val vertex2 = Point(30.0, 200.0)
        val vertex3 = Point(50.0, 10.0)
        mTriangle = Triangle(
                color = color,
                vertex1 = vertex1,
                vertex2 = vertex2,
                vertex3 = vertex3
        )
        mTriangle?.draw(mCanvas)
        verify(mCanvas).setColor(color)
        verify(mCanvas).drawLine(vertex1, vertex2)
        verify(mCanvas).drawLine(vertex2, vertex3)
        verify(mCanvas).drawLine(vertex3, vertex1)
    }

}