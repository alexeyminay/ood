package com.alexey.minay.ood.lab3.streams.input

import com.alexey.minay.ood.lab3.streams.IInputStream

class DecryptInputStream(inputStream: IInputStream, private val key: String) : InputStreamDecorator(inputStream) {

    private var cryptCount = 0

    override fun decorateByte(byte: Int): Int {
        val cryptByte = byte xor key[cryptCount % key.length].toInt()
        cryptCount++
        return cryptByte
    }

    override fun decorateBlock(dstBuffer: (Int) -> Unit): (Int) -> Unit {
        return { dstBuffer(decorateByte(it)) }
    }


}