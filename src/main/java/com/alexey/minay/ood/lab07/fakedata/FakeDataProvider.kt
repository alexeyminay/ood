package com.alexey.minay.ood.lab07.fakedata

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.Slide
import com.alexey.minay.ood.lab07.domain.composite.Group
import com.alexey.minay.ood.lab07.domain.composite.IShape
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor
import com.alexey.minay.ood.lab07.domain.composite.shapes.Ellipse
import com.alexey.minay.ood.lab07.domain.composite.shapes.Rectangle
import com.alexey.minay.ood.lab07.domain.composite.shapes.Triangle
import com.alexey.minay.ood.lab07.domain.composite.style.SimpleFillStyle
import com.alexey.minay.ood.lab07.domain.composite.style.SimpleLineStyle

object FakeDataProvider {

    fun getSlides(): List<Slide> {
        val slides = mutableListOf<Slide>()
        slides.add(createForestSlide())
        slides.add(createHouseSlide())
        return slides
    }

    private fun createHouseSlide(): Slide {
        val slide = mutableListOf<IShape>()
        val house = Rectangle(
            fillStyle = SimpleFillStyle(true, RGBAColor.BROWN),
            lineStyle = SimpleLineStyle(true, RGBAColor.MAROON),
            shapeFrame = Frame(60.0, 220.0, 250.0, 400.0)
        )
        val window = Rectangle(
            fillStyle = SimpleFillStyle(true, RGBAColor.SKY_BLUE),
            lineStyle = SimpleLineStyle(true, RGBAColor.BLUE),
            shapeFrame = Frame(110.0, 170.0, 300.0, 350.0)
        )
        val houseGroup = Group()
        houseGroup.insertShape(house, houseGroup.getShapeCount())
        houseGroup.insertShape(window, houseGroup.getShapeCount())

        val roofGroup = Group()
        val chimney = Rectangle(
            fillStyle = SimpleFillStyle(true, RGBAColor.BLUE),
            lineStyle = SimpleLineStyle(true, RGBAColor.GREEN),
            shapeFrame = Frame(180.0, 200.0, 150.0, 240.0)
        )
        val roof = Triangle(
            fillStyle = SimpleFillStyle(true, RGBAColor.RED),
            lineStyle = SimpleLineStyle(true, RGBAColor.BROWN),
            shapeFrame = Frame(20.0, 260.0, 150.0, 250.0)
        )
        roofGroup.insertShape(chimney, roofGroup.getShapeCount())
        roofGroup.insertShape(roof, roofGroup.getShapeCount())
        houseGroup.insertShape(roofGroup, houseGroup.getShapeCount())

        val sun = Ellipse(
            fillStyle = SimpleFillStyle(true, RGBAColor.YELLOW),
            lineStyle = SimpleLineStyle(true, RGBAColor.YELLOW),
            shapeFrame = Frame(700.0, 800.0, 30.0, 130.0)
        )

        val treeGroup = Group()
        val trunk = Rectangle(
            fillStyle = SimpleFillStyle(true, RGBAColor.MAROON),
            lineStyle = SimpleLineStyle(true, RGBAColor.MAROON),
            shapeFrame = Frame(380.0, 395.0, 350.0, 500.0)
        )
        val branches1 = Triangle(
            fillStyle = SimpleFillStyle(true, RGBAColor.GREEN),
            lineStyle = SimpleLineStyle(true, RGBAColor.GREEN),
            shapeFrame = Frame(360.0, 415.0, 340.0, 380.0)
        )
        val branches2 = Triangle(
            fillStyle = SimpleFillStyle(true, RGBAColor.GREEN),
            lineStyle = SimpleLineStyle(true, RGBAColor.GREEN),
            shapeFrame = Frame(350.0, 425.0, 360.0, 410.0)
        )
        val branches3 = Triangle(
            fillStyle = SimpleFillStyle(true, RGBAColor.GREEN),
            lineStyle = SimpleLineStyle(true, RGBAColor.GREEN),
            shapeFrame = Frame(340.0, 435.0, 380.0, 450.0)
        )
        treeGroup.insertShape(trunk, treeGroup.getShapeCount())
        treeGroup.insertShape(branches1, treeGroup.getShapeCount())
        treeGroup.insertShape(branches2, treeGroup.getShapeCount())
        treeGroup.insertShape(branches3, treeGroup.getShapeCount())

        slide.add(houseGroup)
        slide.add(sun)
        slide.add(treeGroup)
        return slide
    }

    private fun createForestSlide(): Slide {
        val slide = mutableListOf<IShape>()
        slide.add(createTree(302.0, 200.0))
        slide.add(createTree(600.0, 130.0))
        slide.add(createTree(700.0, 780.0))
        slide.add(createTree(520.0, 600.0))
        slide.add(createTree(50.0, 450.0))
        slide.add(createTree(723.0, 100.0))
        slide.add(createTree(630.0, 300.0))
        slide.add(createTree(130.0, 550.0))
        slide.add(createTree(30.0, 200.0))
        slide.add(createTree(400.0, 300.0))
        return slide
    }

    private fun createTree(x: Double, y: Double): IShape {
        val treeGroup = Group()
        val trunkLeft = x
        val trunkRight = x + 15
        val trunkTop = y - 150
        val trunkBottom = y
        val trunk = Rectangle(
            fillStyle = SimpleFillStyle(true, RGBAColor.MAROON),
            lineStyle = SimpleLineStyle(true, RGBAColor.MAROON),
            shapeFrame = Frame(trunkLeft, trunkRight, trunkTop, trunkBottom)
        )
        val branches1 = Triangle(
            fillStyle = SimpleFillStyle(true, RGBAColor.GREEN),
            lineStyle = SimpleLineStyle(true, RGBAColor.GREEN),
            shapeFrame = Frame(
                trunkLeft - 20,
                trunkRight + 20,
                trunkTop - 10,
                trunkBottom - 120
            )
        )
        val branches2 = Triangle(
            fillStyle = SimpleFillStyle(true, RGBAColor.GREEN),
            lineStyle = SimpleLineStyle(true, RGBAColor.GREEN),
            shapeFrame = Frame(
                trunkLeft - 30,
                trunkRight + 30,
                trunkTop + 10,
                trunkBottom - 90
            )
        )
        val branches3 = Triangle(
            fillStyle = SimpleFillStyle(true, RGBAColor.GREEN),
            lineStyle = SimpleLineStyle(true, RGBAColor.GREEN),
            shapeFrame = Frame(
                trunkLeft - 40,
                trunkRight + 40,
                trunkTop + 30,
                trunkBottom - 50
            )
        )
        treeGroup.insertShape(trunk, treeGroup.getShapeCount())
        treeGroup.insertShape(branches1, treeGroup.getShapeCount())
        treeGroup.insertShape(branches2, treeGroup.getShapeCount())
        treeGroup.insertShape(branches3, treeGroup.getShapeCount())
        return treeGroup
    }

}