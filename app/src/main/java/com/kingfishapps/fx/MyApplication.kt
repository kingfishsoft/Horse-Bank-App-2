package com.kingfishapps.fx

import android.app.Application
import com.kingfishapps.di.DaggerApplicationComponent


class MyApplication: Application() {
        // Reference to the application graph that is used across the whole app
        val appComponent = DaggerApplicationComponent.create()
    }
