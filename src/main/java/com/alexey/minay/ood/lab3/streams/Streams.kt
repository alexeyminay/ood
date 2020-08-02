package com.alexey.minay.ood.lab3.streams

interface IOutputStream {
    fun writeByte(data: Int)
    fun writeBlock(srcData:  IntArray, size: Int)
}

interface IInputStream{
    fun readByte(): Int
    fun readBlock(dstBuffer: (Int) -> Unit, size: Int): Int
}