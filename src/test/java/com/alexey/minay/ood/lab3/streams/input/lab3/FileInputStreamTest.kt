package com.alexey.minay.ood.lab3.streams.input.lab3

import com.alexey.minay.ood.lab3.streams.input.FileInputStream
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File

class FileInputStreamTest {

    private lateinit var file: File
    private lateinit var fileInputStream: FileInputStream

    @Test
    fun shouldReadByte() {
        val byte = fileInputStream.readByte()
        assertEquals('t'.toInt(), byte)
    }


    @Test
    fun shouldReadTheSecondByteAfterFirstOne() {
        val firstByte = fileInputStream.readByte()
        val secondByte = fileInputStream.readByte()
        assertEquals('e'.toInt(), secondByte)
    }

    @Test
    fun shouldReadBlock() {
        val bytes = mutableListOf<Int>()
        fun writeBufferInList(byte: Int) {
            bytes.add(byte)
        }
        fileInputStream.readBlock(::writeBufferInList, 2)
        assertEquals('e'.toInt(), bytes[1])
    }

    @Before
    fun setUp() {
        file = File("test")
        file.writeText("test text")
        fileInputStream = FileInputStream(file)
    }

    @After
    fun clean() {
        file.delete()
    }

}