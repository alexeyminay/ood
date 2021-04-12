package com.alexey.minay.ood.lab3.streams.input

import com.alexey.minay.ood.lab3.streams.EOF
import com.alexey.minay.ood.lab3.streams.IInputStream
import java.util.*

class DecompressInputStream(
    inputStream: IInputStream
) : InputStreamDecorator(inputStream) {

    private val mReadByteQueue = ArrayDeque<Int>()
    private var mIsDataByte = true
    private var mLastByte: Int? = null

    override fun decorateByte(byte: Int): Int {
        if (mReadByteQueue.lastOrNull() != EOF) {
            if (mIsDataByte) {
                mReadByteQueue.add(byte)
            } else {
                repeat(byte - 1) {
                    mReadByteQueue.add(mLastByte)
                }
            }
        }
        mLastByte = byte
        mIsDataByte = !mIsDataByte
        return mReadByteQueue.poll() ?: throw RuntimeException("Empty queue")
    }

    override fun decorateBlock(block: IntArray, size: Int): IntArray {
        val decoratedBlock = mutableListOf<Int>()
        repeat(size) { index ->
            try {
                if (index < block.size) {
                    decoratedBlock.add(decorateByte(block[index]))
                } else {
                    mReadByteQueue.poll()?.let { decoratedBlock.add(it) }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        return decoratedBlock.toIntArray()
    }

}