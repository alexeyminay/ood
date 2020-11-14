package com.alexey.minay.ood.lab04.shapes

import com.alexey.minay.ood.lab04.ICanvas
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class RectangleTest {

    private var mRectangle: Rectangle? = null
    private val mCanvas = mock<ICanvas>()

    @Test
    fun shouldDrawRectangle() {
        val color = Color.BLACK
        val leftTop = Point(10.0, 30.0)
        val rightBottom = Point(40.0, 50.0)
        mRectangle = Rectangle(
                color = color,
                leftTop = leftTop,
                rightBottom = rightBottom
        )
        mRectangle?.draw(mCanvas)

        val leftBottom = Point(leftTop.x, rightBottom.y)
        val rightTop = Point(rightBottom.x, leftTop.y)

        verify(mCanvas).setColor(color)
        verify(mCanvas).drawLine(leftTop, leftBottom)
        verify(mCanvas).drawLine(leftBottom, rightBottom)
        verify(mCanvas).drawLine(rightBottom, rightTop)
        verify(mCanvas).drawLine(rightTop, leftTop)

    }

}