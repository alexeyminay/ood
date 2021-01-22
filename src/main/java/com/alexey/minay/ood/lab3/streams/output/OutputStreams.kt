package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream
import java.io.File

class MemoryOutputStream(
        private val memory: MutableList<Byte>
) : IOutputStream {

    override fun writeByte(data: Int) {
        if (data != -1)
            memory.add(data.toByte())
    }

    override fun writeBlock(data: IntArray) {
        data.forEach { writeByte(it) }
    }

}

class FileOutputStream(
        private val file: File
) : IOutputStream {

    override fun writeByte(data: Int) {
        if (data != -1)
            file.appendText(data.toChar().toString(), Charsets.ISO_8859_1)
    }

    override fun writeBlock(data: IntArray) {
        data.forEach { writeByte(it) }
    }

}

