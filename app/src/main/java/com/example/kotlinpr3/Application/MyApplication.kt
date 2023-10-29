package com.example.kotlinpr3.Application

import android.app.Application
import com.example.kotlinpr3.Dagger.AppComponent
import com.example.kotlinpr3.Dagger.AppModule
import com.example.kotlinpr3.Dagger.DaggerAppComponent


class MyApplication: Application() {
    companion object{
         lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .build()

    }
}

