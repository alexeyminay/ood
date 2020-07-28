package com.alexey.minay.ood.lab05.document

class Paragraph(
        text: String
) : IParagraph {

    private var mText: String = text

    override fun getText() = mText

    override fun setText(text: String) {
        mText = text
    }

    override fun toString() = "Paragraph $mText"
}