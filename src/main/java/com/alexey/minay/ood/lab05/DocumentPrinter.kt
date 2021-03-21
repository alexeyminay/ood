package com.alexey.minay.ood.lab05

import com.alexey.minay.ood.lab05.document.IDocument
import com.alexey.minay.ood.lab05.document.IDocumentPrinter

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