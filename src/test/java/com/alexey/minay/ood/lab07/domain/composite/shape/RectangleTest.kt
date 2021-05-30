//package com.alexey.minay.ood.lab07.domain.composite.shape
//
//import com.alexey.minay.ood.lab07.domain.Frame
//import com.alexey.minay.ood.lab07.domain.ICanvas
//import com.alexey.minay.ood.lab07.domain.composite.IFillStyle
//import com.alexey.minay.ood.lab07.domain.composite.ILineStyle
//import com.alexey.minay.ood.lab07.domain.composite.RGBAColor
//import com.alexey.minay.ood.lab07.domain.composite.shapes.Rectangle
//import com.nhaarman.mockitokotlin2.any
//import com.nhaarman.mockitokotlin2.mock
//import com.nhaarman.mockitokotlin2.never
//import com.nhaarman.mockitokotlin2.verify
//import org.junit.Test
//import org.mockito.Mockito
//
//class RectangleTest {
//    private val mCanvas = mock<ICanvas>()
//    private val mStyle = mock<IStyle>()
//    private val mFrame = Frame(2.0, 5.0, 10.0, 12.0)
//    private val mRectangle = Rectangle(
//        style = mStyle,
//        shapeFrame = mFrame
//    )
//
//    @Test
//    fun shouldDrawWithLineColor() {
//        val color = RGBAColor.BLUE
//        val lineStyle = ILineStyle(true, color)
//        Mockito.`when`(mStyle.lineStyle).thenReturn(lineStyle)
//        Mockito.`when`(mStyle.fillStyle).thenReturn(IFillStyle(true, color))
//        mRectangle.draw(mCanvas)
//        verify(mCanvas).setLineColor(color)
//    }
//
//    @Test
//    fun shouldDrawWithoutStrokeLine() {
//        val color = RGBAColor.BLUE
//        val lineStyle = ILineStyle(false, color)
//        Mockito.`when`(mStyle.lineStyle).thenReturn(lineStyle)
//        Mockito.`when`(mStyle.fillStyle).thenReturn(IFillStyle(true, color))
//        mRectangle.draw(mCanvas)
//        verify(mCanvas).setLineColor(RGBAColor.TRANSPARENT)
//    }
//
//    @Test
//    fun shouldDrawTriangle() {
//        val color = RGBAColor.BLUE
//        val lineStyle = ILineStyle(false, color)
//        Mockito.`when`(mStyle.lineStyle).thenReturn(lineStyle)
//        Mockito.`when`(mStyle.fillStyle).thenReturn(IFillStyle(true, color))
//
//        mRectangle.draw(mCanvas)
//
//        val order = Mockito.inOrder(mCanvas)
//        order.verify(mCanvas).moveTo(mFrame.left, mFrame.top)
//        order.verify(mCanvas).lineTo(mFrame.left, mFrame.bottom)
//        order.verify(mCanvas).lineTo(mFrame.right, mFrame.top)
//        order.verify(mCanvas).lineTo(mFrame.left, mFrame.top)
//    }
//
//    @Test
//    fun shouldDrawWithFillColor() {
//        val color = RGBAColor.BLUE
//        val lineStyle = ILineStyle(false, color)
//        Mockito.`when`(mStyle.lineStyle).thenReturn(lineStyle)
//        Mockito.`when`(mStyle.fillStyle).thenReturn(IFillStyle(true, color))
//
//        mRectangle.draw(mCanvas)
//
//        verify(mCanvas).fill(color)
//    }
//
//    @Test
//    fun shouldDrawWithoutFileLine() {
//        val color = RGBAColor.BLUE
//        val lineStyle = ILineStyle(false, color)
//        Mockito.`when`(mStyle.lineStyle).thenReturn(lineStyle)
//        Mockito.`when`(mStyle.fillStyle).thenReturn(IFillStyle(false, color))
//
//        mRectangle.draw(mCanvas)
//
//        verify(mCanvas, never()).fill(any())
//    }
//
//}