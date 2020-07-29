package com.alexey.minay.ood.lab3.streams

import com.alexey.minay.ood.lab3.streams.input.DecryptInputStream
import com.alexey.minay.ood.lab3.streams.input.FileInputStream
import com.alexey.minay.ood.lab3.streams.input.MemoryInputStream
import com.alexey.minay.ood.lab3.streams.output.EncryptOutputStream
import com.alexey.minay.ood.lab3.streams.output.FileOutputStream
import com.alexey.minay.ood.lab3.streams.output.MemoryOutputStream
import java.io.File

fun main() {
    val existsMemory = mutableListOf<Byte>()
    val outputStream = EncryptOutputStream(MemoryOutputStream(existsMemory), "123413241ey")
    outputStream.writeBlock(mutableListOf('s'.toByte(), 'e'.toByte()).toByteArray(), 2)
    outputStream.writeByte('1'.toByte())
    outputStream.writeByte('s'.toByte())
    outputStream.writeByte('5'.toByte())
    outputStream.writeByte('5'.toByte())
    outputStream.writeByte('4'.toByte())

    val inputStream = DecryptInputStream(MemoryInputStream(existsMemory), "123413241ey")
    inputStream.readBlock({ print("${it.toChar()} ") }, 2)
    val b = inputStream.readByte().toChar()
    val b1 = inputStream.readByte().toChar()
    val b2 = inputStream.readByte().toChar()
    val b3 = inputStream.readByte().toChar()
    val b4 = inputStream.readByte().toChar()
    print("$b $b1 $b2 $b3 $b4")
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
    inputStream.readBlock({ print(" ${it.toChar()}") }, 2)
    val b = inputStream.readByte().toChar()
    val b1 = inputStream.readByte().toChar()
    val b2 = inputStream.readByte().toChar()
    val b3 = inputStream.readByte().toChar()
    val b4 = inputStream.readByte().toChar()
    print(" $b $b1 $b2 $b3 $b4")
}
