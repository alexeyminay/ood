package com.alexey.minay.ood.lab3.streams.input

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.File

class DecryptInputStreamTest {

    private lateinit var mFile: File
    private lateinit var mDecryptInputStream: DecryptInputStream

    @Test
    fun shouldDecryptByte() {
        val byte = mDecryptInputStream.readByte()
        Assert.assertEquals('t'.toInt(), byte)
    }


    @Test
    fun shouldDecryptSecondByte() {
        val firstByte = mDecryptInputStream.readByte()
        val secondByte = mDecryptInputStream.readByte()
        Assert.assertEquals('e'.toInt(), secondByte)
    }

    @Test
    fun shouldDecryptBlock() {
        val bytes = mutableListOf<Int>()
        fun writeBufferInList(byte: Int) {
            bytes.add(byte)
        }
        mDecryptInputStream.readBlock(::writeBufferInList, 2)
        Assert.assertEquals('e'.toInt(), bytes[1])
    }

    @Before
    fun setUp() {
        mFile = File("test")
        val key = "123"
        val hashKey = key.hashCode().toString()
        mFile.writeText(('t'.toInt() xor hashKey[0].toInt()).toChar().toString())
        mFile.appendText(('e'.toInt() xor hashKey[1].toInt()).toChar().toString())
        mFile.appendText(('s'.toInt() xor hashKey[2].toInt()).toChar().toString())
        mFile.appendText(('t'.toInt() xor hashKey[3].toInt()).toChar().toString())
        mDecryptInputStream = DecryptInputStream(FileInputStream(mFile), key)
    }

    @After
    fun clean() {
        mFile.delete()
    }
}