package com.alexey.minay.ood.lab06.adapters

import com.alexey.minay.ood.lab06.libs.MPoint
import com.alexey.minay.ood.lab06.libs.ModernGraphicsRender
import com.alexey.minay.ood.lab06.libs.RGBAColor
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class ModernLibAdapterTest {

    private val mModernGraphicsRender = mock<ModernGraphicsRender>()
    private var mModernLibAdapter: ModernLibAdapter? = null

    @Before
    fun setUp() {
        mModernLibAdapter = ModernLibAdapter(mModernGraphicsRender)
    }

    @Test
    fun shouldDrawBlackLine() {
        val startPoint = MPoint(1, 1)
        val endPoint = MPoint(1, 1)
        mModernLibAdapter?.moveTo(startPoint.x, startPoint.y)
        mModernLibAdapter?.lineTo(endPoint.x, endPoint.y)
        val order = Mockito.inOrder(mModernGraphicsRender)
        order.verify(mModernGraphicsRender).beginDraw()
        order.verify(mModernGraphicsRender).drawLine(startPoint, endPoint, RGBAColor.BLACK)
        order.verify(mModernGraphicsRender).endDrawing()
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfDoNotChooseStartPosition() {
        val endPoint = MPoint(1, 1)
        `when`(mModernLibAdapter?.lineTo(endPoint.x, endPoint.y)).thenThrow(RuntimeException("Choose start point"))
    }

    @Test
    fun shouldSetColor() {
        val startPoint = MPoint(1, 1)
        val endPoint = MPoint(1, 1)
        val color = 0x00ff00
        val rgbaColor = RGBAColor(0f, 1f, 0f)
        mModernLibAdapter?.setColor(color)
        mModernLibAdapter?.moveTo(startPoint.x, startPoint.y)
        mModernLibAdapter?.lineTo(endPoint.x, endPoint.y)
        val order = Mockito.inOrder(mModernGraphicsRender)
        order.verify(mModernGraphicsRender).beginDraw()
        order.verify(mModernGraphicsRender).drawLine(startPoint, endPoint, rgbaColor)
        order.verify(mModernGraphicsRender).endDrawing()
    }

}