package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocumentItem

class DeleteItemCommand(
        private val documentItems: MutableList<IDocumentItem>,
        private val position: Int
) : AbstractCommand() {

    private var deletedItem: IDocumentItem? = null

    override fun doExecute() {
        deletedItem = documentItems[position]
        documentItems.removeAt(position)
    }

    override fun doUnExecute() {
        deletedItem?.let { documentItems.add(it) }
    }

}
