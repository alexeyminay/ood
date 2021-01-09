package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.FileHelper
import com.alexey.minay.ood.lab05.document.DocumentItem
import com.alexey.minay.ood.lab05.document.IDocumentItem
import com.alexey.minay.ood.lab05.document.Image

class InsertImageCommand(
        private val documentItems: MutableList<IDocumentItem>,
        private val path: String,
        private val width: Int,
        private val height: Int,
        private val position: Int,
        private val fileHelper: FileHelper
) : AbstractCommand() {

    private val mImageName = "${IMAGE_PREFIX}${System.currentTimeMillis()}"

    override fun doExecute() {
        if (position > documentItems.size) throw RuntimeException("$position is incorrectPosition")
        val image = Image(mImageName, IMAGE_PATH, width, height)
        fileHelper.copyFile(path, image.getPath(), image.getPath() + image.getName())
        val documentItem = DocumentItem(image = image)
        documentItems.add(position, documentItem)
    }

    override fun doUnExecute() {
        documentItems.removeAt(position)
    }

    override fun onClear() {
        fileHelper.deleteFile(IMAGE_PATH + mImageName)
    }

    companion object {

        const val IMAGE_PATH = "image/"
        private const val IMAGE_PREFIX = "img_"

    }

}