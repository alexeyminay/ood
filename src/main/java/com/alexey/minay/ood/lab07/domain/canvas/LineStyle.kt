package com.alexey.minay.ood.lab07.domain.canvas

data class LineStyle(
        val isEnable: Boolean,
        val color: RGBAColor = RGBAColor.TRANSPARENT,
        val lineWidth: Double = 3.0
)