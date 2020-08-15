package com.alexey.minay.ood.lab3.streams.output

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File

class CompressOutputStreamTest {

    private var mFile: File? = null
    private var mCompressOutputStream: CompressOutputStream? = null

    @Test
    fun shouldCompressByte() {
        mCompressOutputStream?.writeByte('t'.toInt())
        mCompressOutputStream?.writeByte('t'.toInt())
        mCompressOutputStream?.writeByte('e'.toInt())
        mCompressOutputStream?.writeByte('e'.toInt())
        val javaInputStream = mFile?.inputStream()
        javaInputStream?.read()
        assertEquals(2.toChar().toInt(), javaInputStream?.read())
    }

    @Test
    fun shouldCompressBlock() {
        val bytes = intArrayOf('t'.toInt(), 't'.toInt(), 'e'.toInt(), 'e'.toInt())
        mCompressOutputStream?.writeBlock(bytes)
        val javaInputStream = mFile?.inputStream()
        val firstByte = javaInputStream?.read()
        val secondByte = javaInputStream?.read()
        assertEquals(2.toChar().toInt(), secondByte)
    }

    @Before
    fun setup() {
        mFile = File("test2")
        mCompressOutputStream = CompressOutputStream(FileOutputStream(mFile!!))
    }

    @After
    fun delete() {
        mFile?.outputStream()?.close()
        mFile?.delete()
    }

}