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

    override fun writeBlock(srcData: IntArray) {
        outputStream.writeBlock(decorateBlock(srcData))
    }

    protected abstract fun decorateByte(data: Int): IntArray

    private fun decorateBlock(srcData: IntArray) = srcData
            .map { decorateByte(it).toMutableList() }
            .flatten()
            .filter { it != -1 }
            .toIntArray()

}