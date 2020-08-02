package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream


class CompressOutputStream(
        outputStream: IOutputStream
) : OutputStreamDecorator(outputStream) {

    private var mLastByte: Int? = null
    private var mCount: Int = 0

    override fun decorateByte(data: Int): IntArray {
        return when (mLastByte) {
            null -> {
                mCount++
                mLastByte = data
                intArrayOf(data)
            }
            data -> {
                mCount++
                intArrayOf(-1)
            }
            else -> {
                val count = mCount
                mCount = 1
                mLastByte = data
                intArrayOf(count, data)
            }
        }
    }

}