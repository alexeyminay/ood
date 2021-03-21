package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.shapes.Shape

class Document {

    private val mShapes = mutableListOf<Shape>()

    //TODO доработать, тут будет утечка памяти, если одно из окон закрыть
    private val mCallbacks = mutableListOf<(List<Shape>) -> Unit>()

    fun getShapeCount() = mShapes.size

    fun getShapes() = mShapes

    fun setShapes(shapes: List<Shape>) {
        mShapes.clear()
        mShapes.addAll(shapes)
        notifyAllSubscribers()
    }

    fun getFramesByUid(uid: Long) = mShapes.firstOrNull { it.uid == uid }

    fun insertShapeAt(index: Int, shape: Shape) {
        mShapes.add(index, shape)
        notifyAllSubscribers()
    }

    fun removeShapeAt(index: Int) = mShapes.removeAt(index).also { notifyAllSubscribers() }

    fun removeShapeBy(uids: List<Long>): Map<Int, Shape> {
        val deletedShapes = mutableMapOf<Int, Shape>()
        mShapes.forEachIndexed { index, shape ->
            if (uids.contains(shape.uid)) {
                deletedShapes[index] = shape
            }
        }
        mShapes.removeAll(deletedShapes.values)
        notifyAllSubscribers()
        return deletedShapes
    }

    fun subscribe(onNext: (List<Shape>) -> Unit) {
        mCallbacks.add(onNext)
        onNext(mShapes)
    }

    fun notifyAllSubscribers() {
        mCallbacks.forEach { onNext ->
            onNext(mShapes)
        }
    }

}