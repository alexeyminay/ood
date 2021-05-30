package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream

abstract class OutputStreamDecorator : IOutputStream {

    override fun writeByte(data: Int) {
        decorateByte(data)
    }

    override fun writeBlock(data: IntArray) {
        data.forEach { decorateByte(it) }
    }

    protected abstract fun decorateByte(byte: Int)

}