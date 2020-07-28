package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument


class CloseDocumentCommand(
        private val document: IDocument
) : ICommand {
    override fun execute() {
        document.close()
    }
}