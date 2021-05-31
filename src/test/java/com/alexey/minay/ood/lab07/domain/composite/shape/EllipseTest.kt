package com.alexey.minay.ood.lab07.domain.composite.shape

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.composite.IFillStyle
import com.alexey.minay.ood.lab07.domain.composite.ILineStyle
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
    private val mFillStyle = mock<IFillStyle>()
    private val mLineStyle = mock<ILineStyle>()
    private val mFrame = Frame(2.0, 5.0, 10.0, 12.0)
    private val mEllipse = Ellipse(
        fillStyle = mFillStyle,
        lineStyle = mLineStyle,
        shapeFrame = mFrame
    )

    @Test
    fun shouldDrawWithLineColor() {
        val color = RGBAColor.BLUE
        Mockito.`when`(mLineStyle.color).thenReturn(color)
        Mockito.`when`(mLineStyle.isEnable).thenReturn(true)
        mEllipse.draw(mCanvas)
        verify(mCanvas).setLineColor(color)
    }

    @Test
    fun shouldDrawWithoutStrokeLine() {
        Mockito.`when`(mLineStyle.isEnable).thenReturn(false)
        mEllipse.draw(mCanvas)
        verify(mCanvas).setLineColor(RGBAColor.TRANSPARENT)
    }

    @Test
    fun shouldDrawEllipse() {
        val width = mFrame.right - mFrame.left
        val height = mFrame.bottom - mFrame.top

        mEllipse.draw(mCanvas)

        val order = Mockito.inOrder(mCanvas)
        order.verify(mCanvas).drawEllipse(mFrame.left, mFrame.top, width, height)
    }

    @Test
    fun shouldDrawWithFillColor() {
        val width = mFrame.right - mFrame.left
        val height = mFrame.bottom - mFrame.top
        val color = RGBAColor.BLUE
        Mockito.`when`(mFillStyle.color).thenReturn(color)
        Mockito.`when`(mFillStyle.isEnable).thenReturn(true)

        mEllipse.draw(mCanvas)

        verify(mCanvas).fillEllipse(color, mFrame.left, mFrame.top, width, height)
    }

    @Test
    fun shouldDrawWithoutFileLine() {
        val color = RGBAColor.BLUE
        Mockito.`when`(mFillStyle.color).thenReturn(color)
        Mockito.`when`(mFillStyle.isEnable).thenReturn(false)

        mEllipse.draw(mCanvas)

        verify(mCanvas, never()).fill(any())
    }

}