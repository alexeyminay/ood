package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument

class SetTitleCommand(
        private val document: IDocument,
        private val title: String
) : ICommand {

    override fun execute() {
        document.setTitle(title)
    }

}