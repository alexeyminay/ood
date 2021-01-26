package com.alexey.minay.ood.lab07.domain.composite

interface IGroup : IShape {
    fun getShapeCount(): Int
    fun getShapeIndexAt(index: Int): IShape
    fun insertShape(shape: IShape, index: Int)
    fun removeAt(index: Int)
}