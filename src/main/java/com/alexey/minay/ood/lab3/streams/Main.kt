package com.alexey.minay.ood.lab3.streams

fun main() {
    val existsMemory = mutableListOf<Byte>()
    existsMemory.add('y'.toByte())
    existsMemory.add(12.toByte())
    existsMemory.add(3)
    val inputMemory = MemoryInputStream(existsMemory)
    val newMemory = mutableListOf<Byte>()
    val outputStream = MemoryOutputStream(newMemory)
    var readingByte = inputMemory.readByte()
    while (readingByte != MemoryInputStream.EOF){
        outputStream.writeByte(readingByte.toByte())
        readingByte = inputMemory.readByte()
    }
    println(newMemory.toString())

}