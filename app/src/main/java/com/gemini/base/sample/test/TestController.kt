package com.gemini.base.sample.test

import com.gemini.base.R
import com.gemini.mvi.Binding
import com.gemini.mvi.MviBaseController
import com.gemini.mvi.UiEvent


class TestController : MviBaseController<UiEvent>(R.layout.content_main) {

    override val binding: Binding get() = fromScope<TestBinding>()
}