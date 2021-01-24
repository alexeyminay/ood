package com.alexey.minay.ood.lab07.ui

import com.alexey.minay.ood.lab07.domain.Slide

class SlidePresenter : MVP.ISlidePresenter {

    private lateinit var mSlideScene: MVP.ISlideSceneView
    private var mCurrentSlidePosition = 0
    private val mSlides = mutableListOf<Slide>()

    init {
        mSlides.addAll(FakeDataProvider.getSlides())
    }

    override fun setScene(scene: MVP.ISlideSceneView) {
        mSlideScene = scene
        mSlideScene.showSlide(mSlides[FIRST_POSITION])
    }

    override fun onGetNextSlide() {
        if (mCurrentSlidePosition < mSlides.lastIndex) {
            mCurrentSlidePosition++
            mSlideScene.showSlide(mSlides[mCurrentSlidePosition])
        }
    }

    override fun onGetPreviousSlide() {
        if (mCurrentSlidePosition > FIRST_POSITION) {
            mCurrentSlidePosition--
            mSlideScene.showSlide(mSlides[mCurrentSlidePosition])
        }
    }

    companion object {

        private const val FIRST_POSITION = 0

    }

}
