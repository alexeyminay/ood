package com.alexey.minay.ood.lab3.streams.input

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File

class DecompressInputStreamTest {

    private lateinit var mFile: File
    private lateinit var mDecompressInputStream: DecompressInputStream

    @Test
    fun shouldDecompressByte() {
        val byte = mDecompressInputStream.readByte()
        assertEquals('t'.toInt(), byte)
    }


    @Test
    fun shouldDecompressThirdByte() {
        val firstByte = mDecompressInputStream.readByte()
        val secondByte = mDecompressInputStream.readByte()
        val thirdByte = mDecompressInputStream.readByte()
        assertEquals('t'.toInt(), thirdByte)
    }

    @Test
    fun shouldDecompressBlock() {
        val block = mDecompressInputStream.readBlock(8)
        block.forEach { print(it.toChar()) }
        assertEquals('e'.toInt(), block[3])
    }

    @Before
    fun setUp() {
        mFile = File("test")
        mFile.writeText('t'.toString())
        mFile.appendText(3.toByte().toChar().toString())
        mFile.appendText('e'.toString())
        mFile.appendText(4.toByte().toChar().toString())
        mFile.appendText('s'.toString())
        mFile.appendText(4.toByte().toChar().toString())
        mDecompressInputStream = DecompressInputStream(FileInputStream(mFile))
    }

    @After
    fun clean() {
        mFile.delete()
    }

}