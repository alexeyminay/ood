package com.alexey.minay.ood.lab04

import com.alexey.minay.ood.lab04.shapes.Shape

interface IShapeFactory {
    fun createShape(description: String): Shape
}