package com.alexey.minay.ood.lab05.document

class DocumentPrinter(
        private val document: IDocument
) : IDocumentPrinter {

    override fun print() {
        println(document.getTitle())
        document.getItems().forEachIndexed { index, documentItem ->
            println("$index: $documentItem")
        }
    }

}