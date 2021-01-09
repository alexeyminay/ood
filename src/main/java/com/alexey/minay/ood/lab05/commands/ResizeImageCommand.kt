package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocumentItem

class ResizeImageCommand(
        private val documentItems: MutableList<IDocumentItem>,
        private val width: Int,
        private val height: Int,
        private val position: Int
) : AbstractCommand() {

    private var mOldWidth: Int = 0
    private var mOldHeight: Int = 0

    override fun doExecute() {
        resize(width, height)
    }

    override fun doUnExecute() {
        resize(mOldWidth, mOldHeight)
    }

    private fun resize(width: Int, height: Int) {
        if (position > documentItems.size) throw RuntimeException("Position $position doesn't exist")
        val item = documentItems[position]
        val image = item.getImage() ?: throw RuntimeException("Item doesn't have image")
        mOldWidth = image.getWidth()
        mOldHeight = image.getHeight()
        image.resize(width, height)
    }

}