package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.DocumentPrinter

class PrintDocumentCommand(
        private val documentPrinter: DocumentPrinter
) : ICommand {

    override fun execute() {
        documentPrinter.print()
    }

}