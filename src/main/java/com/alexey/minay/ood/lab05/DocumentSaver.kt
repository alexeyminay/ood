package com.alexey.minay.ood.lab05

import com.alexey.minay.ood.lab05.document.IDocument
import com.alexey.minay.ood.lab05.dsl.Html
import java.io.File

class DocumentSaver(
        private val document: IDocument
) {

    fun saveHtmlDocument(path: String) {
        val fileName = when {
            document.getTitle().isBlank() -> "new_document"
            else -> document.getTitle()
        }
        val dirPath = File(path)
        if (!dirPath.exists()) dirPath.mkdirs()
        val file = File("$path\\$fileName.html")
        document.getItems().forEach {
            val image = it.getImage()
            if (image != null) {
                writeFile(donorFilePath = image.getPath() + image.getName(),
                        newPath = path + "\\" + IMAGE_PATH,
                        newFilePath = path + "\\" + IMAGE_PATH + image.getName())
            }
        }
        file.writeText(getHtml())
    }

    private fun getHtml() =
            Html {
                head(document.getTitle())
                body {
                    title(document.getTitle())
                    document.getItems().forEach {
                        paragraph(it.getParagraph())
                        image(it.getImage())
                    }
                }
            }

    private fun writeFile(donorFilePath: String, newPath: String, newFilePath: String) {
        val file = File(donorFilePath)
        val tempPathDir = File(newPath)
        if (!tempPathDir.exists()) tempPathDir.mkdir()
        val tempFile = File(newFilePath)
        tempFile.writeBytes(file.readBytes())
    }

    companion object {

        private const val IMAGE_PATH = "image/"

    }

}