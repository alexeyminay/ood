package com.alexey.minay.ood.lab3.streams.input

import com.alexey.minay.ood.lab3.streams.IInputStream

abstract class InputStreamDecorator(
        private val inputStream: IInputStream
) : IInputStream {

    override fun readByte() = decorateByte(inputStream.readByte())

    override fun readBlock(dstBuffer: (Int) -> Unit, size: Int): Int {
        return inputStream.readBlock(decorateBlock(dstBuffer), size)
    }

    protected abstract fun decorateByte(byte: Int): Int

    protected abstract fun decorateBlock(dstBuffer: (Int) -> Unit): (Int) -> Unit

}