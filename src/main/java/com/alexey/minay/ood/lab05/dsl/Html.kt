package com.alexey.minay.ood.lab05.dsl

import com.alexey.minay.ood.lab05.document.IImage
import com.alexey.minay.ood.lab05.document.IParagraph

object Html {
    operator fun invoke(init: HtmlContext.() -> Unit): String {
        val result = HtmlContext(0)
        result.init()
        return result.html
    }
}

@DslHtmlContext
class HtmlContext(
        private val nestingLevel: Int
) {

    private val htmlBuilder: StringBuilder = StringBuilder()
    private val tabs: String = " " * nestingLevel

    internal val html: String
        get() = """
            |$HTML_DOCTYPE
            |<html>
            |$htmlBuilder
            |</html>
             """.trimMargin()

    fun head(title: String) {
        htmlBuilder.append("""
            |<head>
            |$tabs<title>$title</title>
            |</head>""".trimMargin())
    }

    fun body(init: BodyContext.() -> Unit) {
        val bodyContext = BodyContext(nestingLevel + 1)
        init(bodyContext)
        htmlBuilder.append("""
            |
            |<body>
                |${bodyContext.body}
                |</body>""".trimMargin())

    }

    companion object {
        private const val HTML_DOCTYPE = "<!DOCTYPE html>"
    }

}

@DslMarker
annotation class DslHtmlContext

@DslHtmlContext
class BodyContext(
        nestingLevel: Int
) {

    val body = StringBuilder()
    private val tabs: String = " " * nestingLevel

    fun title(title: String) {
        body.append("""
            |$tabs<h1>$title</h1>""".trimMargin())
    }

    fun image(image: IImage?) {
        image ?: return
        body.append("""
            |
            |$tabs<img>src='${image.getPath() + image.getName()}' height=${image.getHeight()} width=${image.getWidth()}</img>
        """.trimMargin())
    }

    fun paragraph(paragraph: IParagraph?) {
        paragraph ?: return
        body.append("""
            |
            |$tabs<p>${paragraph.getText()}</p>""".trimMargin())
    }
}

private operator fun String.times(nestingLevel: Int): String {
    val builder = StringBuilder()
    for (i in 0..nestingLevel) {
        builder.append(" ")
    }
    return builder.toString()
}
