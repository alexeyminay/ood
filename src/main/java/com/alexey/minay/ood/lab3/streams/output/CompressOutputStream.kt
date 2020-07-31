package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream


class CompressOutputStream(
        outputStream: IOutputStream
) : OutputStreamDecorator(outputStream) {

    private var mLastByte: Byte? = null
    private var mCount: Int = 0

    override fun decorateByte(data: Byte): ByteArray {
        return when (mLastByte) {
            null -> {
                mCount++
                mLastByte = data
                byteArrayOf(data)
            }
            data -> {
                mCount++
                byteArrayOf(-1)
            }
            else -> {
                val count = mCount
                mCount = 1
                mLastByte = data
                byteArrayOf(count.toByte(), data)
            }
        }
    }

}