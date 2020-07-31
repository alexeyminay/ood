package com.alexey.minay.ood.lab3.streams

import com.alexey.minay.ood.lab3.streams.input.DecompressInputStream
import com.alexey.minay.ood.lab3.streams.input.DecryptInputStream
import com.alexey.minay.ood.lab3.streams.input.FileInputStream
import com.alexey.minay.ood.lab3.streams.input.MemoryInputStream
import com.alexey.minay.ood.lab3.streams.output.CompressOutputStream
import com.alexey.minay.ood.lab3.streams.output.EncryptOutputStream
import com.alexey.minay.ood.lab3.streams.output.FileOutputStream
import com.alexey.minay.ood.lab3.streams.output.MemoryOutputStream
import java.io.File

fun main() {
    val existsMemory = mutableListOf<Byte>()
    val outputStream = CompressOutputStream(MemoryOutputStream(existsMemory))
    outputStream.writeBlock(mutableListOf('s'.toByte(), 'e'.toByte()).toByteArray(), 2)
    outputStream.writeByte('1'.toByte())
    outputStream.writeByte('s'.toByte())
    outputStream.writeByte('5'.toByte())
    outputStream.writeByte('5'.toByte())
    outputStream.writeByte('5'.toByte())
    outputStream.writeByte('4'.toByte())

    existsMemory.forEachIndexed { i, c ->
        if (i % 2 == 0)
            print("${c.toChar()} ")
        else
            print("$c ")

    }
    println()

    val inputStream = DecompressInputStream(MemoryInputStream(existsMemory))
    //inputStream.readBlock({ print("${it.toChar()} ") }, 2)

    print("${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}")
    //testFileStream()
}

fun testFileStream() {
    val outputFile = File("output")
    val outputStream = EncryptOutputStream(FileOutputStream(outputFile), "key")
    outputStream.writeBlock(mutableListOf('s'.toByte(), 'e'.toByte()).toByteArray(), 2)
    outputStream.writeByte('1'.toByte())
    outputStream.writeByte('s'.toByte())
    outputStream.writeByte('5'.toByte())
    outputStream.writeByte('5'.toByte())
    outputStream.writeByte('4'.toByte())


    val inputStream = DecryptInputStream(FileInputStream(outputFile), "key")
    //inputStream.readBlock({ print(" ${it.toChar()}") }, 2)
    print(" ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}" +
            " ${inputStream.readByte().toChar()}")
}
