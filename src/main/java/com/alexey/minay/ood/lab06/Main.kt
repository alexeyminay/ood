package com.alexey.minay.ood.lab06

import com.alexey.minay.ood.lab06.adapters.ModernLibAdapter
import com.alexey.minay.ood.lab06.adapters.ModernLibClassAdapter
import com.alexey.minay.ood.lab06.libs.*
import java.io.File

fun main() {
    paintPictureOnCanvas()
    paintPictureOnModernGraphicRender()
    paintPictureOnModernGraphicRenderWithClassAdapter()
}

fun paintPicture(painter: CanvasPainter) {
    val triangle = Triangle(Point(10, 15), Point(100, 200), Point(150, 250), 0xff3399)
    val rectangle = Rectangle(Point(30, 40), 18, 24)
    painter.draw(triangle)
    painter.draw(rectangle)
}

fun paintPictureOnCanvas() {
    val simpleCanvas = Canvas()
    val canvasPainter = CanvasPainter(simpleCanvas)
    paintPicture(canvasPainter)
}

fun paintPictureOnModernGraphicRender() {
    val writer = File("adapter.txt").bufferedWriter()
    val modernGraphicsRender = ModernGraphicsRender(writer)
    val modernLibAdapter = ModernLibAdapter(modernGraphicsRender)
    val canvasPainter = CanvasPainter(modernLibAdapter)
    paintPicture(canvasPainter)
    writer.close()
}

fun paintPictureOnModernGraphicRenderWithClassAdapter() {
    val writer = File("ClassAdapter.txt").bufferedWriter()
    val modernGraphicsRender = ModernLibClassAdapter(writer)
    val canvasPainter = CanvasPainter(modernGraphicsRender)
    paintPicture(canvasPainter)
    writer.close()
}