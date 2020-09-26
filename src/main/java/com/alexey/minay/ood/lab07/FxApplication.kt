package com.alexey.minay.ood.lab07

import com.alexey.minay.ood.lab07.ui.SlidePresenter
import com.alexey.minay.ood.lab07.ui.SlideScene
import javafx.application.Application
import javafx.stage.Stage

class FxApplication : Application() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(FxApplication::class.java)
        }
    }

    override fun start(primaryStage: Stage?) {
        val slidePresenter = SlidePresenter()
        val slideScene = SlideScene(primaryStage, slidePresenter)
        slideScene.onStart()
    }

}