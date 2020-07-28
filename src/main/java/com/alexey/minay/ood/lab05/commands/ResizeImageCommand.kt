package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument

class ResizeImageCommand(
        private val document: IDocument,
        private val width: Int,
        private val height: Int,
        private val position: Int = document.getLastPosition()
) : ICommand {

    override fun execute() {
        document.resizeImage(height, width, position)
    }

}