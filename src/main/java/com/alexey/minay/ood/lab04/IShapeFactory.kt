package com.alexey.minay.ood.lab04

interface IShapeFactory {
    fun createShape(description: String): Shape
}