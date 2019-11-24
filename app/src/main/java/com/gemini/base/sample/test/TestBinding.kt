package com.gemini.base.sample.test

import androidx.lifecycle.LifecycleOwner
import com.gemini.mvi.AndroidBinding
import javax.inject.Inject

class TestBinding @Inject constructor(lifecycleOwner: LifecycleOwner) : AndroidBinding<TestController>(lifecycleOwner) {

    override fun bind(view: TestController) {

    }

}