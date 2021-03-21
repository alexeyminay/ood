package com.alexey.minay.ood.lab07.domain.composite.shape

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.composite.FillStyle
import com.alexey.minay.ood.lab07.domain.composite.IStyle
import com.alexey.minay.ood.lab07.domain.composite.LineStyle
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor
import com.alexey.minay.ood.lab07.domain.composite.shapes.Ellipse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito

class EllipseTest {
    private val mCanvas = mock<ICanvas>()
    private val mStyle = mock<IStyle>()
    private val mFrame = Frame(2.0, 5.0, 10.0, 12.0)
    private val mEllipse = Ellipse(
        style = mStyle,
        shapeFrame = mFrame
    )

    @Test
    fun shouldDrawWithLineColor() {
        val color = RGBAColor.BLUE
        val lineStyle = LineStyle(true, color)
        Mockito.`when`(mStyle.lineStyle).thenReturn(lineStyle)
        Mockito.`when`(mStyle.fillStyle).thenReturn(FillStyle(true, color))
        mEllipse.draw(mCanvas)
        verify(mCanvas).setLineColor(color)
    }

    @Test
    fun shouldDrawWithoutStrokeLine() {
        val color = RGBAColor.BLUE
        val lineStyle = LineStyle(false, color)
        Mockito.`when`(mStyle.lineStyle).thenReturn(lineStyle)
        Mockito.`when`(mStyle.fillStyle).thenReturn(FillStyle(true, color))
        mEllipse.draw(mCanvas)
        verify(mCanvas).setLineColor(RGBAColor.TRANSPARENT)
    }

    @Test
    fun shouldDrawEllipse() {
        val width = mFrame.right - mFrame.left
        val height = mFrame.bottom - mFrame.top
        val color = RGBAColor.BLUE
        val lineStyle = LineStyle(false, color)
        Mockito.`when`(mStyle.lineStyle).thenReturn(lineStyle)
        Mockito.`when`(mStyle.fillStyle).thenReturn(FillStyle(true, color))

        mEllipse.draw(mCanvas)

        val order = Mockito.inOrder(mCanvas)
        order.verify(mCanvas).drawEllipse(mFrame.left, mFrame.top, width, height)
    }

    @Test
    fun shouldDrawWithFillColor() {
        val width = mFrame.right - mFrame.left
        val height = mFrame.bottom - mFrame.top
        val color = RGBAColor.BLUE
        val lineStyle = LineStyle(false, color)
        Mockito.`when`(mStyle.lineStyle).thenReturn(lineStyle)
        Mockito.`when`(mStyle.fillStyle).thenReturn(FillStyle(true, color))

        mEllipse.draw(mCanvas)

        verify(mCanvas).fillEllipse(color, mFrame.left, mFrame.top, width, height)
    }

    @Test
    fun shouldDrawWithoutFileLine() {
        val color = RGBAColor.BLUE
        val lineStyle = LineStyle(false, color)
        Mockito.`when`(mStyle.lineStyle).thenReturn(lineStyle)
        Mockito.`when`(mStyle.fillStyle).thenReturn(FillStyle(true, color))

        mEllipse.draw(mCanvas)

        verify(mCanvas, never()).fill(any())
    }

}