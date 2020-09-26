package com.alexey.minay.ood.lab07.domain.composite.group

import com.alexey.minay.ood.lab07.domain.composite.IShape

interface IGroup: IShape {
    fun getShapeCount(): Int
    fun getShapeIndexAt(index: Int): IShape
    fun insertShape(shape: IShape, index: Int)
    fun removeAt(index: Int)
}