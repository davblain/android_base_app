package com.gemini.base.ui.samples.films.di

import com.gemini.base.data.films.FilmLoader
import com.gemini.feature.paging.PagingFeature
import toothpick.config.Module

class FilmControllerModule : Module() {
    init {
        bind(PagingFeature::class.java).toInstance(
            PagingFeature(FilmLoader())
        )
    }

}