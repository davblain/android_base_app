package com.gemini.mvi_ctx

import Bootstrapper
import EmptyBootstrapper
import EmptyNewsPublisher
import Feature
import MviActor
import MviReducer
import NewsPublisher

open class ActorReducerFeature<Wish : Any, in Effect : Any, State : Any, News : Any>(
    initialState: State,
    bootstrapper: Bootstrapper<Wish> = EmptyBootstrapper<Wish>(),
    actor: MviActor<State, Wish, Effect>,
    reducer: MviReducer<State, Effect>,
    newsPublisher: NewsPublisher<Wish, Effect, State, News> = EmptyNewsPublisher()
) : Feature<Wish, Wish, Effect, State, News>(
    initialState = initialState,
    bootstrapper = bootstrapper,
    wishToAction = { wish -> wish },
    actor = actor,
    reducer = reducer,
    newsPublisher = newsPublisher
)