package com.alexey.minay.ood.lab3.streams.input

import com.alexey.minay.ood.lab3.streams.EOF
import com.alexey.minay.ood.lab3.streams.IInputStream

class DecryptInputStream(
        inputStream: IInputStream,
        key: String
) : InputStreamDecorator(inputStream) {

    private var mCryptCount = 0
    private val mHashKey = key.hashCode().toString()

    override fun decorateByte(byte: Int): Int {
        if (byte == EOF) {
            return EOF
        }
        val cryptByte = byte xor mHashKey[mCryptCount % mHashKey.length].toInt()
        mCryptCount++
        return cryptByte
    }

    override fun decorateBlock(block: IntArray, size: Int): IntArray {
        return block.map { decorateByte(it) }.toIntArray()
    }

}