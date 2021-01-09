package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.DocumentItem
import com.alexey.minay.ood.lab05.document.IDocumentItem
import com.alexey.minay.ood.lab05.document.Paragraph

class InsertParagraphCommand(
        private val documentItems: MutableList<IDocumentItem>,
        private val text: String,
        private val position: Int
) : AbstractCommand() {

    override fun doExecute() {
        if (position > documentItems.size) throw RuntimeException("$position is incorrectPosition")
        val paragraph = Paragraph(text)
        val documentItem = DocumentItem(paragraph = paragraph)
        documentItems.add(position, documentItem)
    }

    override fun doUnExecute() {
        documentItems.removeAt(position)
    }

}