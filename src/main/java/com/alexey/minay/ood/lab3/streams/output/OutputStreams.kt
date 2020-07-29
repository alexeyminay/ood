package com.alexey.minay.ood.lab3.streams.output

import com.alexey.minay.ood.lab3.streams.IOutputStream
import java.io.File

class MemoryOutputStream(
        private val memory: MutableList<Byte>
) : IOutputStream {

    override fun writeByte(data: Byte) {
        memory.add(data)
    }

    override fun writeBlock(srcData: ByteArray, size: Int) {
        memory.addAll(srcData.toList())
    }
}

class FileOutputStream(
        private val file: File
) : IOutputStream {

    override fun writeByte(data: Byte) {
        file.appendBytes(ByteArray(1) { data })
    }

    override fun writeBlock(srcData: ByteArray, size: Int) {
        file.appendBytes(srcData)
    }

}

