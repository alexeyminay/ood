package com.alexey.minay.ood.lab07.domain.composite

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas

interface IShape {
    val style: IStyle
    var frame: Frame?
    fun draw(canvas: ICanvas)
}