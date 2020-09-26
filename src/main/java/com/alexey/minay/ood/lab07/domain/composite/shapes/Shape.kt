package com.alexey.minay.ood.lab07.domain.composite.shapes

import com.alexey.minay.ood.lab07.domain.canvas.FillStyle
import com.alexey.minay.ood.lab07.domain.canvas.Frame
import com.alexey.minay.ood.lab07.domain.canvas.LineStyle
import com.alexey.minay.ood.lab07.domain.composite.IShape
import com.alexey.minay.ood.lab07.domain.composite.group.IGroup

abstract class Shape(
        override var fillStyle: FillStyle,
        override var lineStyle: LineStyle,
        override var frame: Frame
) : IShape {

    override var group: IGroup? = null

}