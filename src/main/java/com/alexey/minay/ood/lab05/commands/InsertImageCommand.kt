package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.DocumentItem
import com.alexey.minay.ood.lab05.document.IDocumentItem
import com.alexey.minay.ood.lab05.document.IImage
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
    private var mImage: IImage? = null

    override fun doExecute() {
        if (position > documentItems.size) throw RuntimeException("$position is incorrectPosition")
        if (mImage == null) {
            mImage = Image(mImageName, IMAGE_PATH, width, height)
        }
        mImage?.let { image ->
            image.linkCount++
            fileHelper.copyFile(path, image.getPath(), image.getPath() + image.getName())
            val documentItem = DocumentItem(image = mImage)
            documentItems.add(position, documentItem)
        }
    }

    override fun doUnExecute() {
        documentItems.removeAt(position)
        mImage?.let { it.linkCount-- }
    }

    override fun onClear() {
        if (mImage?.linkCount == 0) {
            fileHelper.deleteFile(IMAGE_PATH + mImageName)
        }
    }

    companion object {

        private const val IMAGE_PATH = "image/"
        private const val IMAGE_PREFIX = "img_"

    }

}