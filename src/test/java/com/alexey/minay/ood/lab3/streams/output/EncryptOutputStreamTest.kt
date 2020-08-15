package com.alexey.minay.ood.lab3.streams.output

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

class EncryptOutputStreamTest {

    private val mMemory = mutableListOf<Int>()
    private val mKey = "1234"
    private val mEncryptOutputStream = EncryptOutputStream(MemoryOutputStream(mMemory), mKey)

    @Test
    fun shouldEncryptByte() {
        mEncryptOutputStream.writeByte('t'.toInt())
        val hashKey = mKey.hashCode().toString()
        val encrypted = 't'.toInt() xor hashKey[0].toInt()
        assertEquals(encrypted, mMemory[0])
    }

    @Test
    fun shouldEncryptBlock() {
        val bytes = intArrayOf('t'.toInt(), 'e'.toInt(), 's'.toInt(), 't'.toInt())
        mEncryptOutputStream.writeBlock(bytes)
        val hashKey = mKey.hashCode().toString()
        val encrypted = 'e'.toInt() xor hashKey[1].toInt()
        assertEquals(encrypted, mMemory[1])
    }

    @After
    fun clear() {
        mMemory.clear()
    }

}
