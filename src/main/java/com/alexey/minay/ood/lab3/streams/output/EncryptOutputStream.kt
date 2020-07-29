package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream

class EncryptOutputStream(
        outputStream: IOutputStream,
        private val key: String
) : OutputStreamDecorator(outputStream) {

    private var cryptCount = 0

    override fun decorateByte(data: Byte): Byte {
        val cryptByte = data.toInt() xor key[cryptCount % key.length].toInt()
        cryptCount++
        return cryptByte.toByte()
    }

    override fun decorateBlock(srcData: ByteArray) = srcData.map { decorateByte(it) }.toByteArray()

}