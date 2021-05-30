package com.alexey.minay.ood.lab07.domain.composite

import com.alexey.minay.ood.lab07.domain.Frame

abstract class Shape(
    override var frame: Frame?,
    override val fillStyle: IFillStyle,
    override val lineStyle: ILineStyle
) : IShape