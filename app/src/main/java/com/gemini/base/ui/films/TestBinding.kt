package com.gemini.base.ui.films

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.binder.using
import com.gemini.base.ui.films.event.UiEventTransformer
import com.gemini.feature.paging.PagingFeature
import com.gemini.mvi.AndroidBinding
import javax.inject.Inject

class TestBinding @Inject constructor(
    lifecycleOwner: LifecycleOwner,
    private val feature: PagingFeature<Film>
) : AndroidBinding<TestController>(lifecycleOwner) {

    override fun bind(view: TestController) {
        binder.bind(view to feature using UiEventTransformer)
        binder.bind(feature to view)
    }

}