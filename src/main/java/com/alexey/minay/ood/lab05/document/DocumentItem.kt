package com.alexey.minay.ood.lab05.document

class DocumentItem(
        image: IImage? = null,
        paragraph: IParagraph? = null
) : IDocumentItem {

    private val mImage: IImage? = image
    private val mParagraph: IParagraph? = paragraph

    override fun getImage() = mImage

    override fun getParagraph() = mParagraph

}