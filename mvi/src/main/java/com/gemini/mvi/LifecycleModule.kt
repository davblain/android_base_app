package com.gemini.mvi

import androidx.lifecycle.LifecycleOwner
import toothpick.config.Module

class LifecycleModule(lifecycleOwner: LifecycleOwner):Module() {
    init {
        bind(LifecycleOwner::class.java).toInstance(lifecycleOwner)
    }
}