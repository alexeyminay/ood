package com.alexey.minay.ood.lab3.streams.input

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File

class FileInputStreamTest {

    private lateinit var mFile: File
    private lateinit var mFileInputStream: FileInputStream

    @Test
    fun shouldReadByte() {
        val byte = mFileInputStream.readByte()
        assertEquals('t'.toInt(), byte)
    }


    @Test
    fun shouldReadTheSecondByteAfterFirstOne() {
        val firstByte = mFileInputStream.readByte()
        val secondByte = mFileInputStream.readByte()
        assertEquals('e'.toInt(), secondByte)
    }

    @Test
    fun shouldReadBlock() {
        val block = mFileInputStream.readBlock(2)
        assertEquals('e'.toInt(), block[1])
    }

    @Before
    fun setUp() {
        mFile = File("test")
        mFile.writeText("test text")
        mFileInputStream = FileInputStream(mFile)
    }

    @After
    fun clean() {
        mFile.delete()
    }

}