package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream

abstract class OutputStreamDecorator(
        private val outputStream: IOutputStream
) : IOutputStream {

    override fun writeByte(data: Int) {
        val decoratedBytes = decorateByte(data)
        decoratedBytes.forEach { decoratedByte ->
            if (decoratedByte != "-1".toInt())
                outputStream.writeByte(decoratedByte)
        }
    }

    override fun writeBlock(srcData: IntArray, size: Int) {
        outputStream.writeBlock(decorateBlock(srcData), size)
    }

    protected abstract fun decorateByte(data: Int): IntArray

    private fun decorateBlock(srcData: IntArray) = srcData
            .map { decorateByte(it).toMutableList() }
            .flatten()
            .toIntArray()

}