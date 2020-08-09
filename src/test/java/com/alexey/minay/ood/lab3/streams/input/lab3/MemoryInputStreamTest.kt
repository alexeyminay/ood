package com.alexey.minay.ood.lab3.streams.input.lab3

import com.alexey.minay.ood.lab3.streams.input.MemoryInputStream
import org.junit.Assert
import org.junit.Test

class MemoryInputStreamTest {

    private val memory = mutableListOf('t'.toInt(), 'e'.toInt(), 's'.toInt(), 't'.toInt())
    private val memoryInputStream = MemoryInputStream(memory)

    @Test
    fun shouldReadByte() {
        val byte = memoryInputStream.readByte()
        Assert.assertEquals('t'.toInt(), byte)
    }


    @Test
    fun shouldReadTheSecondByteAfterFirstOne() {
        val firstByte = memoryInputStream.readByte()
        val secondByte = memoryInputStream.readByte()
        Assert.assertEquals('e'.toInt(), secondByte)
    }

    @Test
    fun shouldReadBlock() {
        val bytes = mutableListOf<Int>()
        fun writeBufferInList(byte: Int) {
            bytes.add(byte)
        }
        memoryInputStream.readBlock(::writeBufferInList, 2)
        Assert.assertEquals('e'.toInt(), bytes[1])
    }

}