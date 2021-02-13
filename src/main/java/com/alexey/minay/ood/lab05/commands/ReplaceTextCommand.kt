package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocumentItem

class ReplaceTextCommand(
    private val documentItem: IDocumentItem,
    private val text: String
) : AbstractCommand() {

    private var oldText: String? = null

    override fun doExecute() {
        val paragraph = documentItem.getParagraph() ?: throw RuntimeException("Item doesn't have paragraph")
        oldText = paragraph.getText()
        paragraph.setText(text)
    }

    override fun doUnExecute() {
        val paragraph = documentItem.getParagraph() ?: throw RuntimeException("Item doesn't have paragraph")
        paragraph.setText(oldText ?: throw RuntimeException("Command didn't execute"))
    }

}