package com.alexey.minay.ood.lab09.domain.domainshapes

data class Shape(
    var frame: Frame,
    val type: ShapeType,
    val uid: Long
)