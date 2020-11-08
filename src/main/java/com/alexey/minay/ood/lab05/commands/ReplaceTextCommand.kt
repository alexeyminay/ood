package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument

class ReplaceTextCommand(
        private val document: IDocument,
        private val text: String,
        private val position: Int = document.getDocumentSize()
) : ICommand {

    override fun execute() {
        document.replaceTextInParagraph(text, position)
    }
}