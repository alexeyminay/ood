package com.alexey.minay.ood.lab3.streams.input

import com.alexey.minay.ood.lab3.streams.IInputStream
import java.io.File

class MemoryInputStream(
        private val memory: List<Byte>
) : IInputStream {

    private var cursor: Int = 0

    override fun readByte() =
            when {
                cursor < memory.size -> memory[cursor++].toInt()
                else -> {
                    cursor = 0
                    -1
                }
            }


    override fun readBlock(dstBuffer: (Int) -> Unit, size: Int): Int {
        var redByte = 0
        for (i in cursor until cursor + size) {
            when (val byte = readByte()) {
                -1 -> Unit
                else -> {
                    dstBuffer(byte)
                    redByte++
                }
            }
        }
        return redByte
    }

}

class FileInputStream(
        file: File
) : IInputStream {

    private val mStream = file.inputStream()

    override fun readByte() = mStream.read()

    override fun readBlock(dstBuffer: (Int) -> Unit, size: Int): Int {
        var redByte = 0
        var byte: Int? = null
        while (byte != EOF && redByte < size) {
            byte = mStream.read()
            dstBuffer(byte)
            redByte++
        }
        return redByte
    }

    companion object{
        const val EOF = -1
    }

}