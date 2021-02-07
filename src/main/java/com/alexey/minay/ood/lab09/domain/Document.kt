package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.shapes.Shape

class Document {

    private val mShapes = mutableListOf<Shape>()

    //TODO доработать, тут будет утечка памяти, если одно из окон закрыть
    private val callbacks = mutableListOf<(List<Shape>) -> Unit>()

    fun getShapeCount() = mShapes.size

    fun getFramesByUid(uid: Long) = mShapes.firstOrNull { it.uid == uid }

    fun insertShapeAt(index: Int, shape: Shape) {
        mShapes.add(index, shape)
        observeAll()
    }

    fun removeShapeAt(index: Int) = mShapes.removeAt(index).also { observeAll() }

    fun removeShapeBy(uids: List<Long>): Map<Int, Shape> {
        val deletedShapes = mutableMapOf<Int, Shape>()
        mShapes.forEachIndexed { index, shape ->
            if (uids.contains(shape.uid)) {
                deletedShapes[index] = shape
            }
        }
        mShapes.removeAll(deletedShapes.values)
        observeAll()
        return deletedShapes
    }

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