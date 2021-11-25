package com.prerna.demo

import com.prerna.news.data.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsDemoApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
    }
}
