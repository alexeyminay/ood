package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument

class RedoCommand(
        private val document: IDocument
) : ICommand {

    override fun execute() {
        document.redo()
    }

}