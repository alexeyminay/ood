package com.alexey.minay.ood.lab06.adapters

import com.alexey.minay.ood.lab06.libs.MPoint
import com.alexey.minay.ood.lab06.libs.RGBAColor
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.io.BufferedWriter
import java.io.File
import kotlin.test.assertEquals

class ModernLibClassAdapterTest {

    private var mFile: File? = null
    private var mBufferedWriter: BufferedWriter? = null
    private var mModernLibAdapter: ModernLibClassAdapter? = null

    @Before
    fun setUp() {
        mFile = File("test")
        mBufferedWriter = mFile?.bufferedWriter()
        mModernLibAdapter = ModernLibClassAdapter(mBufferedWriter!!)
    }

    @After
    fun clear() {
        mBufferedWriter?.close()
        mFile?.delete()
    }

    @Test
    fun shouldDrawBlackLine() {
        val startPoint = MPoint(1, 1)
        val endPoint = MPoint(1, 1)
        mModernLibAdapter?.moveTo(startPoint.x, startPoint.y)
        mModernLibAdapter?.lineTo(endPoint.x, endPoint.y)
        mBufferedWriter?.close()
        val lines = mFile?.readLines()
        assertEquals(" <line fromX=1 fromY=1 toX=1 toX=1>", lines!![1])
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfDoNotChooseStartPosition() {
        val endPoint = MPoint(1, 1)
        Mockito.`when`(mModernLibAdapter?.lineTo(endPoint.x, endPoint.y)).thenThrow(RuntimeException("Choose start point"))
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
        mBufferedWriter?.close()
        val lines = mFile?.readLines()
        assertEquals("  <color r=\"0.0\" g=\"1.0\" b=\"0.0\" a=\"1.0\" />", lines!![2])
    }

}