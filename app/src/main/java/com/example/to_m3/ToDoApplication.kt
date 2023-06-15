package com.example.to_m3

import android.app.Application
import com.example.to_m3.data.AppContainer
import com.example.to_m3.data.AppDataContainer

class ToDoApplication: Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}