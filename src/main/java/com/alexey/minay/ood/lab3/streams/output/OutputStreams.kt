package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream
import java.io.File

class MemoryOutputStream(
    private val memory: MutableList<Byte>
) : IOutputStream {

    override fun writeByte(data: Int) {
        if (data < 0 || data > 255) {
            throw RuntimeException("Incorrect data")
        }
        memory.add(data.toByte())
    }

    override fun writeBlock(data: IntArray) {
        data.forEach { writeByte(it) }
    }

    override fun flush() = Unit

}

class FileOutputStream(
    private val file: File
) : IOutputStream {

    override fun writeByte(data: Int) {
        file.appendText(data.toChar().toString(), Charsets.ISO_8859_1)
    }

    override fun writeBlock(data: IntArray) {
        data.forEach { writeByte(it) }
    }

    override fun flush() = Unit

}

