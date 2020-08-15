package com.alexey.minay.ood.lab3.streams.output

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File

class FileOutputStreamTest {

    private var mFile: File? = null
    private var mMemoryOutputStream: FileOutputStream? = null

    @Test
    fun shouldWriteByte() {
        mMemoryOutputStream?.writeByte('t'.toInt())
        assertEquals('t'.toInt(), mFile?.inputStream()?.read())
    }

    @Test
    fun shouldWriteBlock() {
        val bytes = intArrayOf('t'.toInt(), 'e'.toInt(), 's'.toInt(), 't'.toInt())
        mMemoryOutputStream?.writeBlock(bytes)
        val javaInputStream = mFile?.inputStream()
        val firstByte = javaInputStream?.read()
        val secondByte = javaInputStream?.read()
        javaInputStream?.close()
        assertEquals('e'.toInt(), secondByte)
    }

    @Before
    fun setup() {
        mFile = File("test2")
        mMemoryOutputStream = FileOutputStream(mFile!!)
    }

    @After
    fun delete() {
        mFile?.outputStream()?.close()
        mFile?.delete()
    }
}