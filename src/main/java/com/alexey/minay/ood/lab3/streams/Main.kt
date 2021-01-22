package com.alexey.minay.ood.lab3.streams


import com.alexey.minay.ood.lab3.streams.input.DecompressInputStream
import com.alexey.minay.ood.lab3.streams.input.DecryptInputStream
import com.alexey.minay.ood.lab3.streams.input.FileInputStream
import com.alexey.minay.ood.lab3.streams.output.CompressOutputStream
import com.alexey.minay.ood.lab3.streams.output.EncryptOutputStream
import com.alexey.minay.ood.lab3.streams.output.FileOutputStream
import java.io.File

fun main(args: Array<String>) {
    if (args.size < 2) {
        println("Incorrect params. Input: transform [опции] <input-file> <input-file>")
        println("Options:")
        println("""    • --encrpt <key>. Добавляет шаг шифрования при записи с использованием ключа key. Опция может быть указана несколько раз, что позволяет выполнить несколько этапов шифрования.
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
        println("File $outputFile has already existed. Input nonexistent file name.")
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

    var byte: Int? = null
    while (byte != -1) {
        try {
            byte = inputStream.readByte()
            outputStream.writeByte(byte)
        } catch (e: RuntimeException) {
            println(e.message)
        }
    }

}


//fun main(args: Array<String>) {
//    readBytesFomMemory()
//}
//
//fun readBytesFomMemory() {
//    val memory = mutableListOf<Byte>()
//    val outputStream = MemoryOutputStream(memory)
//    for (i in 0..100) {
//        outputStream.writeByte(i)
//    }
//    val inputStream = MemoryInputStream(memory)
//    var readByte: Int? = null
//    while (readByte != -1) {
//        readByte = inputStream.readByte()
//        println(readByte)
//    }
//}
//
//fun readBlockBytesFomMemory() {
//    val memory = mutableListOf<Byte>()
//    val outputStream = MemoryOutputStream(memory)
//    for (i in 0..100) {
//        outputStream.writeBlock(intArrayOf(i))
//    }
//    val inputStream = MemoryInputStream(memory)
//    var readByte: Int? = null
//    val input = inputStream.readBlock(100)
//    input.forEach {
//        print("$it ")
//    }
//}
//
//fun readBytesFomFile() {
//    val file = File("test.txt")
//    val outputStream = EncryptOutputStream(FileOutputStream(file), "asdf")
//    for (i in 0..100) {
//        outputStream.writeByte(i)
//    }
//    val inputStream = DecryptInputStream(FileInputStream(file), "asdf")
//    var readByte: Int? = null
//    while (readByte != -1) {
//        readByte = inputStream.readByte()
//        println(readByte)
//    }
//}
