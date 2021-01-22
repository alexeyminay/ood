package com.alexey.minay.ood.lab3.streams.input

import org.junit.Assert
import org.junit.Test

class MemoryInputStreamTest {

    private val mMemory = mutableListOf('t'.toByte(), 'e'.toByte(), 's'.toByte(), 't'.toByte())
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
        val block = mMemoryInputStream.readBlock(2)
        Assert.assertEquals('e'.toInt(), block[1])
    }

}