package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream

abstract class OutputStreamDecorator(
        private val outputStream: IOutputStream
) : IOutputStream {

    override fun writeByte(data: Byte) {
        val decoratedBytes = decorateByte(data)
        decoratedBytes.forEach { decoratedByte ->
            if (decoratedByte != "-1".toByte())
                outputStream.writeByte(decoratedByte)
        }
    }

    override fun writeBlock(srcData: ByteArray, size: Int) {
        outputStream.writeBlock(decorateBlock(srcData), size)
    }

    protected abstract fun decorateByte(data: Byte): ByteArray

    private fun decorateBlock(srcData: ByteArray) = srcData
            .map { decorateByte(it).toMutableList() }
            .flatten()
            .toByteArray()

}