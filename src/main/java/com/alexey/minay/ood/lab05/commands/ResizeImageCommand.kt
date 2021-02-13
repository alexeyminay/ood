package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocumentItem

class ResizeImageCommand(
    private val documentItem: IDocumentItem,
    private val width: Int,
    private val height: Int
) : AbstractCommand() {

    private var mOldWidth: Int? = null
    private var mOldHeight: Int? = null

    override fun doExecute() {
        resize(width, height)
    }

    override fun doUnExecute() {
        resize(
            width = mOldWidth ?: throw RuntimeException("Command didn't execute"),
            height = mOldHeight ?: throw RuntimeException("Command didn't execute")
        )
    }

    private fun resize(width: Int, height: Int) {
        val image = documentItem.getImage() ?: throw RuntimeException("Item doesn't have image")
        mOldWidth = image.getWidth()
        mOldHeight = image.getHeight()
        image.resize(width, height)
    }

}