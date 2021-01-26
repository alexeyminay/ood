package com.alexey.minay.ood.lab04

class PictureDraft(
        private val shapes: List<Shape> = mutableListOf()
) {

    fun getShapeCount() = shapes.size

    fun getShape(index: Int) = shapes[index]

}