package com.example.lexilearn

import android.app.Application
import androidx.room.Room
import com.example.lexilearn.data.local.AppDatabase

class BaseApplication : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "lexilearn_db"
        ).build()
    }
}
