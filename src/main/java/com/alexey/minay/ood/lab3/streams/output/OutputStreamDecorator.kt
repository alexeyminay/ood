package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream

abstract class OutputStreamDecorator : IOutputStream {

    override fun writeByte(data: Int) {
        decorateByte(data)
    }

    override fun writeBlock(data: IntArray) {
        decorateBlock(data)
    }

    protected abstract fun decorateByte(byte: Int)

    protected abstract fun decorateBlock(block: IntArray)

}