package com.gemini.base.di.modules

import android.app.Application
import android.content.Context
import toothpick.config.Module

class AppModule(app: Application) : Module() {
    init {
        val context = app.applicationContext
        bind(Context::class.java).toInstance(context)
    }
}