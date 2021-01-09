package com.alexey.minay.ood.lab05

import com.alexey.minay.ood.lab05.document.*

fun main() {
    val history = History()
    val fileHelper = FileHelper()
    val document = Document(history, fileHelper)
    val documentPrinter = DocumentPrinter(document, ::println)
    val documentSaver = DocumentSaver(document)
    println("введите команду:")
    println("Help - помощь")
    while (true) {
        val input = readLine() ?: continue
        val splittedCommand = input.split(" ")
        if (splittedCommand.isEmpty()) continue

        try {
            when (splittedCommand[0]) {
                "InsertParagraph" -> handleInsertParagraphCommand(document, splittedCommand)
                "InsertImage" -> handleInsertImageCommand(document, splittedCommand)
                "SetTitle" -> handleSetTitleCommand(document, splittedCommand)
                "List" -> documentPrinter.print()
                "ReplaceText" -> handleReplaceTextCommand(document, splittedCommand)
                "ResizeImage" -> handleResizeImageCommand(document, splittedCommand)
                "DeleteItem" -> handleDeleteItemCommand(document, splittedCommand)
                "Help" -> Help.print()
                "Undo" -> document.undo()
                "Redo" -> document.redo()
                "Save" -> handleSaveCommand(documentSaver, splittedCommand)
                "Close" -> {
                    document.close()
                    return
                }
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }
}

private fun handleDeleteItemCommand(document: IDocument, splittedCommand: List<String>) {
    if (splittedCommand.size < 2) return
    val position: Int = splittedCommand[1].toIntOrNull() ?: return
    document.deleteItem(position)
}

private fun handleInsertParagraphCommand(document: IDocument, splittedCommand: List<String>) {
    if (splittedCommand.size < 3) return
    val text = splittedCommand.subList(2, splittedCommand.size).joinToString()
    if (splittedCommand[1] == "end") {
        document.insertParagraph(text, null)
    }
    val position: Int = splittedCommand[1].toIntOrNull() ?: return
    document.insertParagraph(text, position)
}

private fun handleInsertImageCommand(document: IDocument, splittedCommand: List<String>) {
    if (splittedCommand.size != 5) throw RuntimeException("Incorrect params")
    val width: Int = splittedCommand[2].toIntOrNull() ?: throw RuntimeException("Incorrect params")
    val height: Int = splittedCommand[3].toIntOrNull() ?: throw RuntimeException("Incorrect params")
    val path: String = splittedCommand[4]
    if (splittedCommand[1] == "end") {
        document.insertImages(path, width, height, null)
    }
    val position: Int = splittedCommand[1].toIntOrNull() ?: throw RuntimeException("Incorrect params")
    document.insertImages(path, width, height, position)
}

private fun handleSetTitleCommand(document: IDocument, splittedCommand: List<String>) {
    if (splittedCommand.size < 2) return
    return document.setTitle(splittedCommand.subList(1, splittedCommand.size).joinToString())
}

private fun handleReplaceTextCommand(document: IDocument, splittedCommand: List<String>) {
    if (splittedCommand.size < 3) return
    val position: Int = splittedCommand[1].toIntOrNull() ?: return
    val text = splittedCommand.subList(2, splittedCommand.size).joinToString()
    document.getItem(position).getParagraph()?.setText(text)
}

private fun handleResizeImageCommand(document: IDocument, splittedCommand: List<String>) {
    if (splittedCommand.size != 4) return
    val position: Int = splittedCommand[1].toIntOrNull() ?: return
    val width: Int = splittedCommand[2].toIntOrNull() ?: return
    val height: Int = splittedCommand[3].toIntOrNull() ?: return
    document.resizeImage(width, height, position)
}

private fun handleSaveCommand(documentSaver: IDocumentSaver, splitCommand: List<String>) {
    if (splitCommand.size != 2) return
    documentSaver.saveHtmlDocument(splitCommand[1])
}


