package com.alexey.minay.ood.lab05.document

class Image(
        name: String,
        path: String,
        width: Int,
        height: Int
) : IImage {

    private val mPath: String = path
    private var mWidth = width
    private var mHeight = height
    private val mName = name

    override var linkCount: Int = 0

    override fun getName() = mName

    override fun getPath() = mPath

    override fun getHeight() = mHeight

    override fun getWidth() = mWidth

    override fun resize(width: Int, height: Int) {
        mWidth = width
        mHeight = height
    }

    override fun toString() = "Image $mWidth $mHeight $mPath$mName"

}