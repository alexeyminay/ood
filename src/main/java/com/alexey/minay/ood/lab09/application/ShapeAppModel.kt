package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.domain.IShape
import com.alexey.minay.ood.lab09.domain.shapes.Frame

class ShapeAppModel(
    val shape: IShape
) {

    val frame: Frame
        get() = shape.frame

}