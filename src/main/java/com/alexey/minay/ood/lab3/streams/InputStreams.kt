package com.alexey.minay.ood.lab3.streams

import java.io.File

class MemoryInputStream(
        private val memory: List<Byte>
) : IInputStream {

    private var cursor: Int = 0

    override fun readByte() =
            when {
                cursor >= memory.size -> memory[cursor++].toInt()
                else -> -1
            }


    override fun readBlock(dstBuffer: (Int) -> Unit, size: Int): Int {
        var redByte = 0
        for (i in cursor..cursor + size) {
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
        private val file: File
) : IInputStream {

    private val stream = file.inputStream()

    override fun readByte() = stream.read()

    override fun readBlock(dstBuffer: (Int) -> Unit, size: Int): Int {
        var redByte = 0
        var byte = stream.read()
        while (byte != -1) {
            byte = stream.read()
            dstBuffer(byte)
            redByte++
        }
        return redByte
    }

}