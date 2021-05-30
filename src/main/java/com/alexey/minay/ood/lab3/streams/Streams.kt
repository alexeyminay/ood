package com.alexey.minay.ood.lab3.streams

const val EOF = -1

interface IOutputStream {
    fun writeByte(data: Int)
    fun writeBlock(data: IntArray)
    fun flush()
}

interface IInputStream {
    fun readByte(): Int
    fun readBlock(size: Int): IntArray
}