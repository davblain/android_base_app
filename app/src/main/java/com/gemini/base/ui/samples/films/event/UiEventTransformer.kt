package com.gemini.base.ui.samples.films.event

import com.gemini.feature.paging.PagingFeature

object UiEventTransformer : (UiEvent) -> PagingFeature.Wish? {
    override fun invoke(event: UiEvent): PagingFeature.Wish? = when (event) {
        is UiEvent.ButtonClicked -> PagingFeature.Wish.LoadMoreItems
    }
}