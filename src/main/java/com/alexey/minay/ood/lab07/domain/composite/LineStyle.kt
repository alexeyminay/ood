package com.alexey.minay.ood.lab07.domain.composite

data class LineStyle(
        val isEnable: Boolean,
        val color: RGBAColor = RGBAColor.TRANSPARENT,
        val lineWidth: Double = 3.0
)