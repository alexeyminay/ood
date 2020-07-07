package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument

class SaveCommand(
        private val document: IDocument,
        private val path: String
) : ICommand {

    override fun execute() {
        document.save(path)
    }

}