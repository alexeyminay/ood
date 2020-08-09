package com.alexey.minay.ood.lab3.streams.input.lab3

import com.alexey.minay.ood.lab3.streams.input.DecryptInputStream
import com.alexey.minay.ood.lab3.streams.input.FileInputStream
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.File

class DecryptInputStreamTest {

    private lateinit var file: File
    private lateinit var decryptInputStream: DecryptInputStream

    @Test
    fun shouldDecryptByte() {
        val byte = decryptInputStream.readByte()
        Assert.assertEquals('t'.toInt(), byte)
    }


    @Test
    fun shouldDecryptSecondByte() {
        val firstByte = decryptInputStream.readByte()
        val secondByte = decryptInputStream.readByte()
        Assert.assertEquals('e'.toInt(), secondByte)
    }

    @Test
    fun shouldDecryptBlock() {
        val bytes = mutableListOf<Int>()
        fun writeBufferInList(byte: Int) {
            bytes.add(byte)
        }
        decryptInputStream.readBlock(::writeBufferInList, 2)
        Assert.assertEquals('e'.toInt(), bytes[1])
    }

    @Before
    fun setUp() {
        file = File("test")
        val key = "123"
        val hashKey = key.hashCode().toString()
        file.writeText(('t'.toInt() xor hashKey[0].toInt()).toChar().toString())
        file.appendText(('e'.toInt() xor hashKey[1].toInt()).toChar().toString())
        file.appendText(('s'.toInt() xor hashKey[2].toInt()).toChar().toString())
        file.appendText(('t'.toInt() xor hashKey[3].toInt()).toChar().toString())
        decryptInputStream = DecryptInputStream(FileInputStream(file), key)
    }

    @After
    fun clean() {
        file.delete()
    }
}