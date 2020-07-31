package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream

class EncryptOutputStream(
        outputStream: IOutputStream,
        private val key: String
) : OutputStreamDecorator(outputStream) {

    private var mCryptCount = 0
    private val mHashKey = key.hashCode().toString()

    override fun decorateByte(data: Byte): ByteArray {
        val cryptByte = data.toInt() xor mHashKey[mCryptCount % key.length].toInt()
        mCryptCount++
        return byteArrayOf(cryptByte.toByte())
    }

}