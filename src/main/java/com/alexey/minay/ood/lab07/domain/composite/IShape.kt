package com.alexey.minay.ood.lab07.domain.composite

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas

interface IShape {
    val fillStyle: IFillStyle
    val lineStyle: ILineStyle
    var frame: Frame?
    fun draw(canvas: ICanvas)
}