package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream


class CompressOutputStream(
    private val outputStream: IOutputStream
) : OutputStreamDecorator() {

    private var mLastByte: Int? = null
    private var mCount: Int = 0

    override fun decorateByte(byte: Int) {
        if (mLastByte == null) {
            mLastByte = byte
            outputStream.writeByte(byte)
        }
        if (byte == mLastByte && mCount < MAX_COUNT) {
            mCount++
        } else {
            mLastByte = byte
            outputStream.writeByte(mCount)
            outputStream.writeByte(byte)
            mCount = 1
        }
    }

    override fun flush() {
        outputStream.writeByte(mCount)
        mCount = 0
        mLastByte = null
    }

    companion object {
        private const val MAX_COUNT = 255
    }

}