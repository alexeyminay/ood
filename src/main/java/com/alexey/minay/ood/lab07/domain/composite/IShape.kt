package com.alexey.minay.ood.lab07.domain.composite

import com.alexey.minay.ood.lab07.domain.canvas.FillStyle
import com.alexey.minay.ood.lab07.domain.canvas.Frame
import com.alexey.minay.ood.lab07.domain.canvas.ICanvas
import com.alexey.minay.ood.lab07.domain.canvas.LineStyle
import com.alexey.minay.ood.lab07.domain.composite.group.IGroup

interface IShape {
    var fillStyle: FillStyle
    var lineStyle: LineStyle
    var frame: Frame
    var group: IGroup?
    fun draw(canvas: ICanvas)
}