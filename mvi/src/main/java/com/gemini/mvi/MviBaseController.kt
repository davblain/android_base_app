package com.gemini.mvi

import android.view.View
import core.BaseController
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject

import toothpick.Scope


abstract class MviBaseController<T>(layout: Int) : BaseController(layout), ObservableSource<T> {

    abstract val binding: Binding

    override fun installModules(scope: Scope) {
        super.installModules(scope)
        scope.installModules(LifecycleModule(this))
    }

    final override fun initializeView(view: View) {
        super.initializeView(view)
        binding.setup(this)
        initView(view)
    }

    abstract fun initView(view: View)

    private val uiEvents = PublishSubject.create<T>()

    protected fun emit(event: T) {
        uiEvents.onNext(event)
    }

    override fun subscribe(observer: Observer<in T>) {
        uiEvents.subscribe(observer)
    }


}