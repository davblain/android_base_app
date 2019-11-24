package com.gemini.feature.paging

import com.gemini.feature.paging.PagingFeature.State
import com.gemini.feature.paging.PagingFeature.Wish
import com.gemini.feature.paging.PagingFeature.Effect
import com.gemini.feature.paging.PagingFeature.News
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.Observable.empty
import io.reactivex.Observable.just
import io.reactivex.android.schedulers.AndroidSchedulers


class PagingFeature<T : Any> (pageLoader: ItemPageLoader<T>) :
    ActorReducerFeature<Wish, Effect, State<T>, News>(
        initialState = State<T>(),
        bootstrapper = BootStrapperImpl(),
        actor = ActorImpl<T>(pageLoader),
        reducer = ReducerImpl(),
        newsPublisher = NewsPublisherImpl()
    ) {

    data class State<T>(
        val loading: Boolean = false,
        val currentPage: Int = 0,
        val currentItems: List<T> = emptyList(),
        val hasNextPage: Boolean = true
    )

    sealed class Wish {
        object LoadMoreItems : Wish()
    }

    sealed class Effect {
        object Loading : Effect()
        data class PageLoaded<T : Any>(val page: Page<T>) : Effect()
    }

    sealed class News

    class BootStrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> = just(Wish.LoadMoreItems)
    }

    class ActorImpl<T : Any>(
        private val pageLoader: ItemPageLoader<T>
    ) : Actor<State<T>, Wish, Effect> {
        override fun invoke(state: State<T>, wish: Wish): Observable<Effect> = when (wish) {
            is Wish.LoadMoreItems -> if (!state.loading)
                pageLoader.getPage(page = state.currentPage + 1)
                    .map<Effect> { Effect.PageLoaded(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(Effect.Loading)
            else empty()
        }
    }

    class ReducerImpl<T : Any> : Reducer<State<T>, Effect> {
        override fun invoke(state: State<T>, effect: Effect): State<T> = when (effect) {
            is Effect.Loading -> state.copy(loading = true)
            is Effect.PageLoaded<*> -> {
                val page = effect.page as Page<T>
                state.copy(
                    loading = false,
                    currentPage = page.page,
                    currentItems = state.currentItems.plus(page.items),
                    hasNextPage = page.hasNextPage
                )
            }
        }
    }

    class NewsPublisherImpl<T> : NewsPublisher<Wish, Effect, State<T>, News> {
        override fun invoke(wish: Wish, effect: Effect, state: State<T>): News? = null
    }
}
