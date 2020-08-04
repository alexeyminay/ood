package com.alexey.minay.ood.lab3.streams.input

import com.alexey.minay.ood.lab3.streams.IInputStream
import com.sun.jmx.remote.internal.ArrayQueue
import java.util.*

class DecompressInputStream(
        inputStream: IInputStream
) : InputStreamDecorator(inputStream){

    private var mQueueBytes: Queue<Int> = LinkedList()
    private var mLastByte: Int? = null
    private var mCount = 0

    override fun decorateByte(byte: Int): Int {
        if (byte == -1 && mQueueBytes.isEmpty()) {
            return -1
        }
        if (mLastByte == null) {
            mLastByte = byte
            mCount++
            mQueueBytes.add(byte)
            return mQueueBytes.poll()
        }
        return if (mCount % 2 != 0) {
            for (i in 0 until byte - 1) mQueueBytes.add(mLastByte)
            mCount++
            mQueueBytes.poll() ?: -2
        } else {
            mLastByte = byte
            mCount++
            mQueueBytes.add(byte)
            mQueueBytes.poll()
        }
    }

    override fun decorateBlock(dstBuffer: (Int) -> Unit): (Int) -> Unit {
        return { dstBuffer(decorateByte(it)) }
    }

}