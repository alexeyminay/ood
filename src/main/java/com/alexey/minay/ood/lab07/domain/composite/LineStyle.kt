package com.alexey.minay.ood.lab07.domain.composite

import com.alexey.minay.ood.lab07.domain.RGBAColor

data class LineStyle(
        val isEnable: Boolean,
        val color: RGBAColor = RGBAColor.TRANSPARENT,
        val lineWidth: Double = 3.0
)