package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument

class InsertImageCommand(
        private val document: IDocument,
        private val path: String,
        private val width: Int,
        private val height: Int,
        private val position: Int
) : ICommand {

    override fun execute() {
        document.insertImages(path, height, width, position)
    }

}