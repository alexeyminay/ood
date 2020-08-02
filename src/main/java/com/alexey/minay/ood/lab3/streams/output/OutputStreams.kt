package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream
import java.io.File

class MemoryOutputStream(
        private val memory: MutableList<Int>
) : IOutputStream {

    override fun writeByte(data: Int) {
        memory.add(data)
    }

    override fun writeBlock(srcData: IntArray, size: Int) {
        memory.addAll(srcData.toList())
    }
}

class FileOutputStream(
        private val file: File
) : IOutputStream {

    override fun writeByte(data: Int) {
        file.appendText(data.toChar().toString())
    }

    override fun writeBlock(srcData: IntArray, size: Int) {
        srcData.forEach {
            writeByte(it)
        }
    }

}

