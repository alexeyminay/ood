package com.alexey.minay.ood.lab3.streams.input

import com.alexey.minay.ood.lab3.streams.IInputStream
import com.sun.jmx.remote.internal.ArrayQueue
import java.util.*

class CompressInputStream(
        inputStream: IInputStream
) : InputStreamDecorator(inputStream){

//    private var mLastByte: Int? = null
//    private var mCount: Int = 0

//    override fun decorateByte(byte: Int): Int {
//        if (byte < 0) throw NumberFormatException("Byte must more then 0")
//        return when (mLastByte) {
//            null -> {
//                mCount++
//                mLastByte = byte
//                byte
//            }
//            byte -> {
//                mCount++
//                -1
//            }
//            else -> {
//                val count = mCount
//                mCount = 0
//                mLastByte = byte
//                count
//            }
//        }
//    }

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
            for (i in 0..byte) mQueueBytes.add(byte)
            mQueueBytes.poll()
        } else {
            mLastByte = byte
            mCount++
            byte
        }
    }

    override fun decorateBlock(dstBuffer: (Int) -> Unit): (Int) -> Unit {

    }

}