package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.application.common.asAppShape
import com.alexey.minay.ood.lab09.application.common.asDomainShape
import com.alexey.minay.ood.lab09.domain.Document
import com.alexey.minay.ood.lab09.domain.shapes.Shape

class DocumentAdapter(
    private val document: Document
) {

    fun getAllState() = document.getShapes()

    fun setShape(shapes: List<Shape>) {
        document.setShapes(shapes)
    }

    fun insertShapeAt(index: Int, shape: IAppShape) {
        document.insertShapeAt(index, shape.asDomainShape())
    }

    fun removeShapeAt(index: Int): IAppShape = document.removeShapeAt(index).asAppShape()

    fun removeShapesBy(uids: List<Long>) = document.removeShapeBy(uids)

    fun getShapeCount() = document.getShapeCount()

    fun getFramesByUid(uid: Long) = document.getFramesByUid(uid)

    fun observeAll() {
        document.observeAll()
    }

}