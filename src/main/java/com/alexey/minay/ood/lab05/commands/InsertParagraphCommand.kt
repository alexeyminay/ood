package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument

class InsertParagraphCommand(
        private val document: IDocument,
        private val text: String,
        private val position: Int
): ICommand {

    override fun execute() {
        document.insertParagraph(text, position)
    }

}