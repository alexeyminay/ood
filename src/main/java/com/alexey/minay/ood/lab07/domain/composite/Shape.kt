package com.alexey.minay.ood.lab07.domain.composite

import com.alexey.minay.ood.lab07.domain.Frame

abstract class Shape(
        override var fillStyle: FillStyle,
        override var lineStyle: LineStyle,
        override var frame: Frame
) : IShape {

    override var group: IGroup? = null

}