package com.alexey.minay.ood.lab3.streams.input

import com.alexey.minay.ood.lab3.streams.EOF
import com.alexey.minay.ood.lab3.streams.IInputStream
import java.io.File

class MemoryInputStream(
        private val memory: List<Byte>
) : IInputStream {

    private var cursor: Int = 0

    override fun readByte(): Int =
            when (cursor < memory.size) {
                true -> when (memory[cursor] >= 0) {
                    true -> memory[cursor++].toInt()
                    false -> memory[cursor++].toInt() + 128
                }
                false -> {
                    cursor = 0
                    -1
                }
            }

    override fun readBlock(size: Int): IntArray {
        val block = mutableListOf<Int>()
        var readByte = readByte()
        var readByteCount = 0
        while (readByte != EOF && readByteCount != size) {
            block.add(readByte)
            readByteCount++
            readByte = readByte()
        }
        return block.toIntArray()
    }

}

class FileInputStream(
        val file: File
) : IInputStream {

    private val mStream = file.inputStream()

    override fun readByte() = mStream.read()

    override fun readBlock(size: Int): IntArray {
        val block = mutableListOf<Int>()
        var readByte = readByte()
        var readByteCount = 0
        while (readByte != EOF && readByteCount != size) {
            block.add(readByte)
            readByteCount++
            readByte = readByte()
        }
        return block.toIntArray()
    }

    companion object {
        const val EOF = -1
    }

}