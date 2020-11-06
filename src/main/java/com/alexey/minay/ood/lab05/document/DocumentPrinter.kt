package com.alexey.minay.ood.lab05.document

class DocumentPrinter(
        private val document: IDocument,
        private val printer: (String) -> Unit
) : IDocumentPrinter {

    override fun print() {
        printer(document.getTitle())
        document.getItems().forEachIndexed { index, documentItem ->
            printer("$index: $documentItem")
        }
    }

}