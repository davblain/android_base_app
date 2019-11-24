package com.gemini.mvi

import androidx.lifecycle.LifecycleOwner


@Suppress("UNCHECKED_CAST")
abstract class AndroidBinding<T:Any>(lifecycleOwner:LifecycleOwner) : Binding(lifecycleOwner) {

   abstract fun bind(view: T)

    override fun setup(view: Any) {
        bind(view as T)
    }
}