package ir.noori.learningandroid

import android.app.Application
import android.content.Context
import android.os.Handler
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MyApplication : Application() {
    companion object {
        lateinit var context: Context
        lateinit var handler: Handler
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        handler = Handler()
    }
}