package com.gemini.base.ui.samples.films

import android.view.View
import com.gemini.base.R
import com.gemini.base.ui.samples.films.event.UiEvent
import com.gemini.base.ui.samples.films.di.FilmControllerModule
import com.gemini.feature.paging.PagingFeature
import com.gemini.mvi.Binding
import com.gemini.mvi.MviBaseController
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.content_main.view.*
import timber.log.Timber
import toothpick.Scope

class TestController : MviBaseController<UiEvent>(R.layout.content_main),
    Consumer<PagingFeature.State<Film>> {

    override fun installModules(scope: Scope) {
        super.installModules(scope)
        scope.installModules(FilmControllerModule())
    }

    override val binding: Binding get() = fromScope<TestBinding>()

    override fun initView(view: View) = with(view) {
        loadMoreButton.setOnClickListener {
            emit(UiEvent.ButtonClicked)
        }
    }

    override fun accept(state: PagingFeature.State<Film>) {
        Timber.d(state.toString())
    }


}