package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocumentSaver

class SaveCommand(
        private val documentSaver: IDocumentSaver,
        private val path: String
) : ICommand {

    override fun execute() {
        documentSaver.saveHtmlDocument(path)
    }

}