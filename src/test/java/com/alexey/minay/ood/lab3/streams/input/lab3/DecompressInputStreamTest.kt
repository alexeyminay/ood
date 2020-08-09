package com.alexey.minay.ood.lab3.streams.input.lab3

import com.alexey.minay.ood.lab3.streams.input.DecompressInputStream
import com.alexey.minay.ood.lab3.streams.input.FileInputStream
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File

class DecompressInputStreamTest {

    private lateinit var file: File
    private lateinit var decompressInputStream: DecompressInputStream

    @Test
    fun shouldDecompressByte() {
        val byte = decompressInputStream.readByte()
        Assert.assertEquals('t'.toInt(), byte)
    }


    @Test
    fun shouldDecompressThirdByte() {
        val firstByte = decompressInputStream.readByte()
        val secondByte = decompressInputStream.readByte()
        val thirdByte = decompressInputStream.readByte()
        Assert.assertEquals('t'.toInt(), thirdByte)
    }

    @Test
    fun shouldDecompressBlock() {
        val bytes = mutableListOf<Int>()
        fun writeBufferInList(byte: Int) {
            bytes.add(byte)
        }
        decompressInputStream.readBlock(::writeBufferInList, 5)
        assertEquals('e'.toInt(), bytes[4])
    }

    @Before
    fun setUp() {
        file = File("test")
        file.writeText('t'.toString())
        file.appendBytes(ByteArray(1) { 3 })
        file.appendText('e'.toString())
        file.appendBytes(ByteArray(1) { 4 })
        decompressInputStream = DecompressInputStream(FileInputStream(file))
    }

    @After
    fun clean() {
        file.delete()
    }

}