//package com.alexey.minay.ood.lab07.domain.composite.shape
//
//import com.alexey.minay.ood.lab07.domain.Frame
//import com.alexey.minay.ood.lab07.domain.ICanvas
//import com.alexey.minay.ood.lab07.domain.composite.LineStyle
//import com.alexey.minay.ood.lab07.domain.RGBAColor
//import com.alexey.minay.ood.lab07.domain.composite.FillStyle
//import com.alexey.minay.ood.lab07.domain.composite.shapes.Ellipse
//import com.nhaarman.mockitokotlin2.any
//import com.nhaarman.mockitokotlin2.mock
//import com.nhaarman.mockitokotlin2.never
//import com.nhaarman.mockitokotlin2.verify
//import org.junit.Test
//import org.mockito.Mockito
//
//class EllipseTest {
//    private val mCanvas = mock<ICanvas>()
//    private val mFillStyle = mock<FillStyle>()
//    private val mLineStyle = mock<LineStyle>()
//    private val mFrame = mock<Frame>()
//    private val mEllipse = Ellipse(
//            fillStyle = mFillStyle,
//            lineStyle = mLineStyle,
//            frame = mFrame
//    )
//
//    @Test
//    fun shouldDrawWithLineColor() {
//        val color = RGBAColor.BLUE
//        Mockito.`when`(mLineStyle.isEnable).thenReturn(true)
//        Mockito.`when`(mLineStyle.color).thenReturn(color)
//
//        mEllipse.draw(mCanvas)
//
//        verify(mCanvas).setLineColor(color)
//    }
//
//    @Test
//    fun shouldDrawWithoutStrokeLine() {
//        Mockito.`when`(mLineStyle.isEnable).thenReturn(false)
//        Mockito.`when`(mLineStyle.color).thenReturn(RGBAColor.BLUE)
//
//        mEllipse.draw(mCanvas)
//
//        verify(mCanvas).setLineColor(RGBAColor.TRANSPARENT)
//    }
//
//    @Test
//    fun shouldDrawTriangle() {
//        val bottom = 1.0
//        val top = 3.0
//        val right = 3.0
//        val left = 1.0
//        val width = 2.0
//        val height = -2.0
//        Mockito.`when`(mFrame.bottom).thenReturn(bottom)
//        Mockito.`when`(mFrame.top).thenReturn(top)
//        Mockito.`when`(mFrame.right).thenReturn(right)
//        Mockito.`when`(mFrame.left).thenReturn(left)
//
//        mEllipse.draw(mCanvas)
//
//        val order = Mockito.inOrder(mCanvas)
//        order.verify(mCanvas).drawEllipse(mFrame.left, mFrame.top, width, height)
//    }
//
//    @Test
//    fun shouldDrawWithFillColor() {
//        val bottom = 1.0
//        val top = 3.0
//        val right = 3.0
//        val left = 1.0
//        val width = 2.0
//        val height = -2.0
//        Mockito.`when`(mFrame.bottom).thenReturn(bottom)
//        Mockito.`when`(mFrame.top).thenReturn(top)
//        Mockito.`when`(mFrame.right).thenReturn(right)
//        Mockito.`when`(mFrame.left).thenReturn(left)
//
//        val color = RGBAColor.BLUE
//        Mockito.`when`(mFillStyle.isEnable).thenReturn(true)
//        Mockito.`when`(mFillStyle.color).thenReturn(color)
//
//        mEllipse.draw(mCanvas)
//
//        verify(mCanvas).fillEllipse(color, left, top, width, height)
//    }
//
//    @Test
//    fun shouldDrawWithoutFileLine() {
//        Mockito.`when`(mFillStyle.isEnable).thenReturn(false)
//        Mockito.`when`(mFillStyle.color).thenReturn(RGBAColor.BLUE)
//
//        mEllipse.draw(mCanvas)
//
//        verify(mCanvas, never()).fill(any())
//    }
//
//}