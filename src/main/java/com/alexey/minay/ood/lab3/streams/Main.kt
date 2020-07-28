package com.alexey.minay.ood.lab3.streams

import com.alexey.minay.ood.lab3.streams.input.DecryptInputStream
import com.alexey.minay.ood.lab3.streams.input.MemoryInputStream
import java.io.File
import java.nio.charset.Charset

/*fun main() {
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
    while (readingByte != MemoryInputStream.EOF) {
        memoryOutputStream.writeByte(readingByte.toByte())
        fileOutputStream.writeByte(readingByte.toByte())
        readingByte = inputMemory.readByte()
    }
    println(newMemory.toByteArray().toString(Charset.defaultCharset()))

}*/

fun main(){
    val existsMemory = mutableListOf<Byte>()
    existsMemory.add('f'.toByte())
    existsMemory.add('1'.toByte())
    existsMemory.add(90)
    val inputStream = DecryptInputStream(MemoryInputStream(existsMemory), "key")
    inputStream.readBlock(::readingData, 1)
}

fun readingData(byte: Int){
    val e = byte
}

/*fun main() {
    val text = "Этот текст я зашифрую"
    val key = "3435353"
    val encryptedText = crypt(text, key)
    println("encrypted text: $encryptedText")
    val decryptedText = crypt(encryptedText, key)
    println("decrypt text: $decryptedText")

}*/

private fun crypt(text: String, key: String) =
        text.mapIndexed { index, char ->
            (char.toInt() xor key[index % key.length].toInt()).toChar()
        }.joinToString("")
