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

fun main(args: Array<String>) {
    if (args.size < 3) {
        println("Incorrect params. Input: transform [опции] <input-file> <input-file>")
        println("Options:")
        println("""    • --encrypt <key>. Добавляет шаг шифрования при записи с использованием ключа key. Опция может быть указана несколько раз, что позволяет выполнить несколько этапов шифрования.
    • --decrypt <key>. Добавляет шаг дешифрования при чтении с использованием ключа key. Опция может быть указана несколько раз, что позволяет выполнить несколько этапов дешифрования.
    • --compress. Добавляет шаг компрессии при записи
    • --decompress. Добавляет шаг декомпресии при чтении""")
        return
    }
    val inputFile = File(args[0])
    if (!inputFile.exists()) {
        println("File $inputFile doesn't exist")
        return
    }
    val outputFile = File(args[1])
    if (outputFile.exists()) {
        println("File $inputFile has already existed. Input nonexistent file name.")
        return
    }
    var inputStream: IInputStream = FileInputStream(inputFile)
    var outputStream: IOutputStream = FileOutputStream(outputFile)
    args.forEachIndexed { index, arg ->
        when (arg) {
            "--encrypt" -> outputStream = EncryptOutputStream(outputStream, args[index + 1])
            "--decrypt" -> inputStream = DecryptInputStream(inputStream, args[index + 1])
            "--compress" -> outputStream = CompressOutputStream(outputStream)
            "--decompress" -> inputStream = DecompressInputStream(inputStream)
        }
    }

    var byte = inputStream.readByte()
    while (byte != -1) {
        outputStream.writeByte(byte)
        byte = inputStream.readByte()
    }

}


//fun main() {
//    val existsMemory = mutableListOf<Int>()
//    val outputStream = CompressOutputStream(EncryptOutputStream(MemoryOutputStream(existsMemory), "key"))
//    outputStream.writeBlock(mutableListOf('s'.toInt(), 'e'.toInt()).toIntArray(), 2)
//    outputStream.writeByte('1'.toInt())
//    outputStream.writeByte('s'.toInt())
//    outputStream.writeByte('5'.toInt())
//    outputStream.writeByte('5'.toInt())
//    outputStream.writeByte('5'.toInt())
//    outputStream.writeByte('5'.toInt())
//    outputStream.writeByte('4'.toInt())
//
//    existsMemory.forEachIndexed { i, c ->
//        if (i % 2 == 0)
//            print("${c.toChar()} ")
//        else
//            print("$c ")
//
//    }
//    println()
//
//    val inputStream = DecompressInputStream(DecryptInputStream(MemoryInputStream(existsMemory), "key"))
//    inputStream.readBlock({ print("${it.toChar()} ") }, 8)
//
//    print("${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}" +
//            " ${inputStream.readByte().toChar()}")
//    testFileStream()
//}
//
//fun testFileStream() {
//    val outputFile = File("ou")
//    val outputStream = EncryptOutputStream(FileOutputStream(outputFile), "key")
//    outputStream.writeBlock(mutableListOf('s'.toInt(), 'e'.toInt()).toIntArray(), 2)
//    outputStream.writeByte('1'.toInt())
//    outputStream.writeByte('s'.toInt())
//    outputStream.writeByte('5'.toInt())
//    outputStream.writeByte('5'.toInt())
//    outputStream.writeByte('4'.toInt())
//
//    val inputStream = DecryptInputStream(FileInputStream(outputFile), "key")
//    inputStream.readBlock({ print("${it.toChar()} ") }, 2)
//    print("${inputStream.readByte().toChar()} " +
//            "${inputStream.readByte().toChar()} " +
//            "${inputStream.readByte().toChar()} " +
//            "${inputStream.readByte().toChar()} " +
//            "${inputStream.readByte().toChar()} " +
//            "${inputStream.readByte().toChar()} " +
//            "${inputStream.readByte().toChar()} " +
//            "${inputStream.readByte().toChar()} " +
//            "${inputStream.readByte().toChar()} " +
//            "${inputStream.readByte().toChar()} " +
//            "${inputStream.readByte().toChar()} " )
//}
