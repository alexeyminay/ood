package com.alexey.minay.ood.lab07.domain.canvas

sealed class LineType {
    class Shapes(val lineWidth: Double) : LineType()
    object Frame : LineType()
}