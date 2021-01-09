package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocumentItem

class ReplaceTextCommand(
        private val documentItems: MutableList<IDocumentItem>,
        private val text: String,
        private val position: Int
) : AbstractCommand() {

    private var oldText: String? = null

    override fun doExecute() {
        changeTextIntoParagraph(text)
    }

    override fun doUnExecute() {
        changeTextIntoParagraph(oldText ?: "")
    }

    private fun changeTextIntoParagraph(text: String) {
        if (position > documentItems.size) throw RuntimeException("Position $position doesn't exist")
        val item = documentItems[position]
        val paragraph = item.getParagraph() ?: throw RuntimeException("Item doesn't have paragraph")
        oldText = paragraph.getText()
        paragraph.setText(text)
    }

}