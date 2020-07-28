package com.alexey.minay.ood.lab04

import com.alexey.minay.ood.lab04.shapes.Shape

class PictureDraft(
        private val shapes: List<Shape> = mutableListOf()
) {

    fun getShapeCount() = shapes.size

    fun getShape(index: Int) = shapes[index]

}