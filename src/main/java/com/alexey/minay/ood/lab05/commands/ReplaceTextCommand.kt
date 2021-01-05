package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument

class ReplaceTextCommand(
        private val document: IDocument,
        private val text: String,
        private val position: Int = document.getDocumentSize()
) : ICommand {

    override fun execute() {
        if (position > document.getItems().size) throw RuntimeException("Position $position doesn't exist")
        val item = document.getItems()[position]
        val paragraph = item.getParagraph() ?: throw RuntimeException("Item doesn't have paragraph")
        paragraph.setText(text)
    }
}