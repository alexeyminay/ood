package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream

class EncryptOutputStream(
        outputStream: IOutputStream,
        private val key: String
) : OutputStreamDecorator(outputStream) {

    private var mCryptCount = 0
    private val mHashKey = key.hashCode().toString()

    override fun decorateByte(data: Int): IntArray {
        val cryptByte = data xor mHashKey[mCryptCount % key.length].toInt()
        mCryptCount++
        return intArrayOf(cryptByte)
    }

}