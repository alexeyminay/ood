package com.alexey.minay.ood.lab09.domain.style

sealed class Style {
    object Frame : Style()
    class Shape(
            val strokeColor: Color,
            val fillColor: Color
    ) : Style()
}