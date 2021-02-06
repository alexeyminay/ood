package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.application.common.asAppShape
import com.alexey.minay.ood.lab09.application.common.asDomainShape
import com.alexey.minay.ood.lab09.domain.Document

class DocumentAdapter(
    private val document: Document
) {

    fun insertShapeAt(index: Int, shape: IAppShape) {
        document.insertShapeAt(index, shape.asDomainShape())
    }

    fun removeShapeAt(index: Int): IAppShape = document.removeShapeAt(index).asAppShape()

    fun getShapeCount() = document.getShapeCount()

    fun getFramesByUid(uid: Long) = document.getFramesByUid(uid)

    fun observeAll() {
        document.observeAll()
    }

}