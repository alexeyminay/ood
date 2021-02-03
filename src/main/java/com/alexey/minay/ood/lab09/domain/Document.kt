package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.domainshapes.Shape

class Document {

    private val mShapes = mutableListOf<Shape>()
    private var mOnNext: (List<Shape>) -> Unit = {}

    fun getShapeCount() = mShapes.size

    fun getShape(index: Int): Shape = mShapes[index]

    fun insertShapeAt(index: Int, shape: Shape) {
        mShapes.add(index, shape)
        mOnNext(mShapes)
    }

    fun removeShapeAt(index: Int) = mShapes.removeAt(index).also { mOnNext(mShapes) }

    fun subscribe(onNext: (List<Shape>) -> Unit) {
        mOnNext = onNext
    }

}