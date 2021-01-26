package com.alexey.minay.ood.lab04

import com.alexey.minay.ood.lab04.shapes.Color
import com.alexey.minay.ood.lab04.shapes.ICanvas

abstract class Shape(
        open val color: Color
) {

    abstract fun draw(canvas: ICanvas)

}