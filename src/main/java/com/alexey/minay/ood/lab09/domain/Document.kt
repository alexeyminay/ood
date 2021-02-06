package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.domainshapes.Shape

class Document {

    private val mShapes = mutableListOf<Shape>()

    //TODO переписать, тут будет утечка памяти
    private val callbacks = mutableListOf<(List<Shape>) -> Unit>()

    fun getShapeCount() = mShapes.size

    fun getFramesByUid(uid: Long) = mShapes.firstOrNull { it.uid == uid }

    fun insertShapeAt(index: Int, shape: Shape) {
        mShapes.add(index, shape)
        observeAll()
    }

    fun removeShapeAt(index: Int) = mShapes.removeAt(index).also { observeAll() }

    fun subscribe(onNext: (List<Shape>) -> Unit) {
        callbacks.add(onNext)
        observeAll()
    }

    fun observeAll() {
        callbacks.forEach { onNext ->
            onNext(mShapes)
        }
    }

}