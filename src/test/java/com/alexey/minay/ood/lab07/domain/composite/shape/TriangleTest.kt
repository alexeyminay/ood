package com.alexey.minay.ood.lab07.domain.composite.shape

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.composite.IFillStyle
import com.alexey.minay.ood.lab07.domain.composite.ILineStyle
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor
import com.alexey.minay.ood.lab07.domain.composite.shapes.Triangle
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito

class TriangleTest {

    private val mCanvas = mock<ICanvas>()
    private val mFillStyle = mock<IFillStyle>()
    private val mLineStyle = mock<ILineStyle>()
    private val mFrame = Frame(2.0, 5.0, 10.0, 12.0)
    private val mTriangle = Triangle(
        fillStyle = mFillStyle,
        lineStyle = mLineStyle,
        shapeFrame = mFrame
    )

    @Test
    fun shouldDrawWithLineColor() {
        val color = RGBAColor.BLUE
        Mockito.`when`(mLineStyle.color).thenReturn(color)
        Mockito.`when`(mLineStyle.isEnable).thenReturn(true)
        mTriangle.draw(mCanvas)
        verify(mCanvas).setLineColor(color)
    }

    @Test
    fun shouldDrawWithoutStrokeLine() {
        Mockito.`when`(mLineStyle.isEnable).thenReturn(false)
        mTriangle.draw(mCanvas)
        verify(mCanvas).setLineColor(RGBAColor.TRANSPARENT)
    }

    @Test
    fun shouldDrawTriangle() {
        val middle = (mFrame.right - mFrame.left) / 2 + mFrame.left

        mTriangle.draw(mCanvas)

        val order = Mockito.inOrder(mCanvas)
        order.verify(mCanvas).moveTo(middle, mFrame.top)
        order.verify(mCanvas).lineTo(mFrame.left, mFrame.bottom)
        order.verify(mCanvas).lineTo(mFrame.right, mFrame.bottom)
        order.verify(mCanvas).lineTo(middle, mFrame.top)
    }

    @Test
    fun shouldDrawWithFillColor() {
        val color = RGBAColor.BLUE
        Mockito.`when`(mFillStyle.color).thenReturn(color)
        Mockito.`when`(mFillStyle.isEnable).thenReturn(true)

        mTriangle.draw(mCanvas)

        verify(mCanvas).fill(color)
    }

    @Test
    fun shouldDrawWithoutFileLine() {
        val color = RGBAColor.BLUE
        Mockito.`when`(mFillStyle.color).thenReturn(color)
        Mockito.`when`(mFillStyle.isEnable).thenReturn(false)

        mTriangle.draw(mCanvas)

        verify(mCanvas, never()).fill(any())
    }

}