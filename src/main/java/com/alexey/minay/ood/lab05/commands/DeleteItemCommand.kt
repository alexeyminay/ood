package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument

class DeleteItemCommand(
        private val document: IDocument,
        private val position: Int
) : ICommand {

    override fun execute() {
        document.deleteItem(position)
    }

}
