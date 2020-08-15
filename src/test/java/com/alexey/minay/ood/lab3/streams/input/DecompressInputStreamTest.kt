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
        val bytes = mutableListOf<Int>()
        fun writeBufferInList(byte: Int) {
            bytes.add(byte)
        }
        mDecompressInputStream.readBlock(::writeBufferInList, 5)
        assertEquals('e'.toInt(), bytes[4])
    }

    @Before
    fun setUp() {
        mFile = File("test")
        mFile.writeText('t'.toString())
        mFile.appendBytes(ByteArray(1) { 3 })
        mFile.appendText('e'.toString())
        mFile.appendBytes(ByteArray(1) { 4 })
        mDecompressInputStream = DecompressInputStream(FileInputStream(mFile))
    }

    @After
    fun clean() {
        mFile.delete()
    }

}