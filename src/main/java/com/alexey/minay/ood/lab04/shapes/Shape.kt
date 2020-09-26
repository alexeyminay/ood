package com.alexey.minay.ood.lab04.shapes

import com.alexey.minay.ood.lab04.ICanvas

abstract class Shape(
        open val color: Color
) {

    abstract fun draw(canvas: ICanvas)

}