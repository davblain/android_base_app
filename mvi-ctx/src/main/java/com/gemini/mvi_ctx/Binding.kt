package com.gemini.mvi_ctx

import Binder
import androidx.lifecycle.LifecycleOwner

abstract class Binding<T : Any>(lifecycleOwner: LifecycleOwner) {
    protected val binder = Binder(lifecycleOwner.lifecycle)

    abstract fun setup(view: T)
}