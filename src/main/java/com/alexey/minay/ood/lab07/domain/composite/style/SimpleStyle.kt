package com.alexey.minay.ood.lab07.domain.composite.style

import com.alexey.minay.ood.lab07.domain.composite.FillStyle
import com.alexey.minay.ood.lab07.domain.composite.IStyle
import com.alexey.minay.ood.lab07.domain.composite.LineStyle


class SimpleStyle(
    override var fillStyle: FillStyle?,
    override var lineStyle: LineStyle?
) : IStyle