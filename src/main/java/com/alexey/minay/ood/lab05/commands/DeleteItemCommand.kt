package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.FileHelper
import com.alexey.minay.ood.lab05.document.IDocumentItem

class DeleteItemCommand(
    private val documentItems: MutableList<IDocumentItem>,
    private val position: Int,
    private val fileHelper: FileHelper
) : AbstractCommand() {

    private var mDeletedItem: IDocumentItem? = null

    override fun doExecute() {
        mDeletedItem = documentItems[position]
        documentItems.removeAt(position)
        mDeletedItem?.getImage()?.let { it.linkCount-- }
    }

    override fun doUnExecute() {
        mDeletedItem?.let { documentItems.add(it) }
        mDeletedItem?.getImage()?.let { it.linkCount++ }
    }

    override fun onClear() {
        if (mDeletedItem?.getImage()?.linkCount == 0)
            fileHelper.deleteFile(
                mDeletedItem?.getImage()?.getPath()
                        + mDeletedItem?.getImage()?.getName()
            )
    }

}
