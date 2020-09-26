package com.alexey.minay.ood.lab07.ui

import com.alexey.minay.ood.lab07.domain.Slide

interface MVP {
    interface ISlidePresenter {
        fun setScene(scene: ISlideSceneView)
        fun onGetNextSlide()
        fun onGetPreviousSlide()
    }

    interface ISlideSceneView {
        fun showSlide(slide: Slide)
    }
}