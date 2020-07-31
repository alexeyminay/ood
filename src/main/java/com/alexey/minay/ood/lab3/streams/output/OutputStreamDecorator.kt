package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream

abstract class OutputStreamDecorator(
        private val outputStream: IOutputStream
) : IOutputStream {

    override fun writeByte(data: Byte) {
        val decoratedByte = decorateByte(data)
        if(decoratedByte != "-1".toByte())
            outputStream.writeByte(decoratedByte)
    }

    override fun writeBlock(srcData: ByteArray, size: Int) {
        outputStream.writeBlock(decorateBlock(srcData), size)
    }

    protected abstract fun decorateByte(data: Byte): Byte

    protected abstract fun decorateBlock(srcData: ByteArray): ByteArray

}