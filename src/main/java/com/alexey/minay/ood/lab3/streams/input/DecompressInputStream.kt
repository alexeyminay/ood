package com.alexey.minay.ood.lab3.streams.input

import com.alexey.minay.ood.lab3.streams.EOF
import com.alexey.minay.ood.lab3.streams.IInputStream
import java.util.*

class DecompressInputStream(
    private val inputStream: IInputStream
) : InputStreamDecorator(inputStream) {

    private val mReadByteQueue = ArrayDeque<Int>()
    private var mIsDataByte = true
    private var mLastByte: Int? = null

    override fun decorateByte(byte: Int): Int {
        if (byte == EOF) {
            return mReadByteQueue.poll() ?: EOF
        }
        if (mIsDataByte) {
            mReadByteQueue.add(byte)
        } else {
            repeat(byte - 1) {
                mLastByte?.let { mReadByteQueue.add(it) }
            }
        }
        mLastByte = byte
        mIsDataByte = !mIsDataByte
        return mReadByteQueue.poll() ?: decorateByte(inputStream.readByte())
    }

}