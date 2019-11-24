package com.gemini.mvi

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.lifecycle.CreateDestroyBinderLifecycle
import com.badoo.mvicore.binder.Binder


abstract class Binding(lifecycleOwner: LifecycleOwner) {
    protected val binder = Binder(
            lifecycle = CreateDestroyBinderLifecycle(
                    androidLifecycle = lifecycleOwner.lifecycle
            )
    )

    abstract fun setup(view: Any)


}