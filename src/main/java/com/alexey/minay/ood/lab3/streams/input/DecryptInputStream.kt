package com.alexey.minay.ood.lab3.streams.input

import com.alexey.minay.ood.lab3.streams.IInputStream

class DecryptInputStream(inputStream: IInputStream, private val key: String) : InputStreamDecorator(inputStream) {

    private var mCryptCount = 0
    private val mHashKey = key.hashCode().toString()

    override fun decorateByte(byte: Int): Int {
        if (byte == -1) {
            return -1
        }
        val cryptByte = byte xor mHashKey[mCryptCount % key.length].toInt()
        mCryptCount++
        return cryptByte
    }

    override fun decorateBlock(dstBuffer: (Int) -> Unit): (Int) -> Unit {
        return { dstBuffer(decorateByte(it)) }
    }


}