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
        if (mLastByte == null) {
            mLastByte = byte
            mCount++
            return byte
        }
        return if (mCount % 2 != 0) {
            for (i in 0 until byte - 1) mQueueBytes.add(mLastByte)
            mCount++
            mQueueBytes.poll() ?: -1
        } else {
            mLastByte = byte
            mCount++
            byte
        }
    }

    override fun decorateBlock(dstBuffer: (Int) -> Unit): (Int) -> Unit {
        return { dstBuffer(decorateByte(it)) }
    }

}