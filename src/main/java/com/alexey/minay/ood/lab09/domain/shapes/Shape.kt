package com.alexey.minay.ood.lab09.domain.shapes

data class Shape(
    val frame: Frame,
    val type: ShapeType,
    val uid: Long
) {

    fun copy() = Shape(frame.copy(), type, uid)

    fun update(newFrame: Frame?) {
        newFrame ?: return
        frame.leftTop.x = newFrame.leftTop.x
        frame.leftTop.y = newFrame.leftTop.y
        frame.rightBottom.x = newFrame.rightBottom.x
        frame.rightBottom.y = newFrame.rightBottom.y
    }

}