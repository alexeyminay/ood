package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream

class EncryptOutputStream(
        private val outputStream: IOutputStream,
        key: String
) : OutputStreamDecorator() {

    private var mCryptCount = 0
    private val mHashKey = key.hashCode().toString()

    override fun decorateByte(byte: Int) {
        val cryptByte = byte xor mHashKey[mCryptCount % mHashKey.length].toInt()
        mCryptCount++
        outputStream.writeByte(cryptByte)
    }

    override fun decorateBlock(block: IntArray) =
            block.forEach { decorateByte(it) }

}