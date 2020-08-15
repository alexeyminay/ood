package com.alexey.minay.ood.lab3.streams.input

import org.junit.Assert
import org.junit.Test

class MemoryInputStreamTest {

    private val mMemory = mutableListOf('t'.toInt(), 'e'.toInt(), 's'.toInt(), 't'.toInt())
    private val mMemoryInputStream = MemoryInputStream(mMemory)

    @Test
    fun shouldReadByte() {
        val byte = mMemoryInputStream.readByte()
        Assert.assertEquals('t'.toInt(), byte)
    }


    @Test
    fun shouldReadTheSecondByteAfterFirstOne() {
        val firstByte = mMemoryInputStream.readByte()
        val secondByte = mMemoryInputStream.readByte()
        Assert.assertEquals('e'.toInt(), secondByte)
    }

    @Test
    fun shouldReadBlock() {
        val bytes = mutableListOf<Int>()
        fun writeBufferInList(byte: Int) {
            bytes.add(byte)
        }
        mMemoryInputStream.readBlock(::writeBufferInList, 2)
        Assert.assertEquals('e'.toInt(), bytes[1])
    }

}