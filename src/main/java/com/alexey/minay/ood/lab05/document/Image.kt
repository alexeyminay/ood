package com.alexey.minay.ood.lab05.document

class Image(
        path: String,
        width: Int,
        height: Int
) : IImage {

    private var mPath: String = path
    private var mWidth = width
    private var mHeight = height

    override fun getPath() = mPath

    override fun getHeight() = mHeight

    override fun getWidth() = mWidth

    override fun resize(width: Int, height: Int) {
        mWidth = width
        mHeight = height
    }
}