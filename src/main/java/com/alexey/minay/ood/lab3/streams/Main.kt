package com.alexey.minay.ood.lab3.streams

import java.io.File
import java.nio.charset.Charset

fun main() {
    val existsMemory = mutableListOf<Byte>()
    existsMemory.add('f'.toByte())
    existsMemory.add('1'.toByte())
    existsMemory.add(90)
    val inputMemory = MemoryInputStream(existsMemory)
    val newMemory = mutableListOf<Byte>()
    val memoryOutputStream = MemoryOutputStream(newMemory)
    val file = File("file")
    val fileOutputStream = FileOutputStream(file)
    var readingByte = inputMemory.readByte()
    while (readingByte != MemoryInputStream.EOF){
        memoryOutputStream.writeByte(readingByte.toByte())
        fileOutputStream.writeByte(readingByte.toByte())
        readingByte = inputMemory.readByte()
    }
    println(newMemory.toByteArray().toString(Charset.defaultCharset()))

}