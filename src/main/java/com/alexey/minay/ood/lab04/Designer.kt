package com.alexey.minay.ood.lab04

import com.alexey.minay.ood.lab04.shapes.Shape
import java.io.InputStream

class Designer(
        private val factory: IShapeFactory
) : IDesigner {

    override fun createDraft(stream: InputStream): PictureDraft {
        val shapes = mutableListOf<Shape>()
        stream.bufferedReader().forEachLine {
            shapes.add(factory.createShape(it))
        }
        return PictureDraft(shapes)
    }

}