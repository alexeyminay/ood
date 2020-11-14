package com.alexey.minay.ood.lab04.shapes

import com.alexey.minay.ood.lab04.ICanvas
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class EllipseTest {


    private var mEllipse: Ellipse? = null
    private val mCanvas = mock<ICanvas>()

    @Test
    fun shouldDrawEllipse() {
        val color = Color.BLACK
        val center = Point(100.0, 200.0)
        val horizontalRadius = 60
        val verticalRadius = 20
        mEllipse = Ellipse(
                color = color,
                center = center,
                horizontalRadius = horizontalRadius,
                verticalRadius = verticalRadius
        )
        mEllipse?.draw(mCanvas)
        verify(mCanvas).setColor(color)
        verify(mCanvas).drawEllipse(center, horizontalRadius, verticalRadius)
    }

}