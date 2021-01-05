package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument

class ResizeImageCommand(
        private val document: IDocument,
        private val width: Int,
        private val height: Int,
        private val position: Int = document.getDocumentSize()
) : ICommand {

    override fun execute() {
        if (position > document.getItems().size) throw RuntimeException("Position $position doesn't exist")
        val item = document.getItems()[position]
        val image = item.getImage() ?: throw RuntimeException("Item doesn't have image")
        image.resize(width, height)
    }

}