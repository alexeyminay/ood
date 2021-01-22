package com.alexey.minay.ood.lab3.streams.input

import com.alexey.minay.ood.lab3.streams.IInputStream

abstract class InputStreamDecorator(
        private val inputStream: IInputStream
) : IInputStream {

    override fun readByte() = decorateByte(inputStream.readByte())

    override fun readBlock(size: Int) = decorateBlock(inputStream.readBlock(size), size)

    protected abstract fun decorateByte(byte: Int): Int

    protected abstract fun decorateBlock(block: IntArray, size: Int): IntArray

}