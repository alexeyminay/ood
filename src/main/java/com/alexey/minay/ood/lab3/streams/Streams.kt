package com.alexey.minay.ood.lab3.streams

interface IOutputStream {
    fun writeByte(data: Int)
    fun writeBlock(data: IntArray)
}

interface IInputStream {
    fun readByte(): Int
    fun readBlock(size: Int): IntArray
}