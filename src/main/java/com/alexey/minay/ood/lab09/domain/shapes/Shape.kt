package com.alexey.minay.ood.lab09.domain.shapes

data class Shape(
    var frame: Frame,
    val type: ShapeType,
    val uid: Long
)