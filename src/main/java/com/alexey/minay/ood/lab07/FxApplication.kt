package com.alexey.minay.ood.lab07

import javafx.application.Application
import javafx.stage.Stage
import com.alexey.minay.ood.lab07.ui.SlidePresenter
import com.alexey.minay.ood.lab07.ui.SlideScene

class FxApplication : Application() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(FxApplication::class.java)
        }
    }

    override fun start(primaryStage: Stage?) {
        val slidePresenter = SlidePresenter()
        val slideScene = SlideScene(primaryStage, slidePresenter)
        slideScene.onStart()
    }

}