package com.alexey.minay.ood.lab07.domain.composite

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas

interface IShape {
    var fillStyle: FillStyle
    var lineStyle: LineStyle
    var frame: Frame
    var group: IGroup?
    fun draw(canvas: ICanvas)
}