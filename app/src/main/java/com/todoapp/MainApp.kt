package com.todoapp

import android.app.Application
import androidx.room.Room
import com.todoapp.db.TodoDatabase

class MainApp : Application() {
    companion object {
        lateinit var todoDatabase: TodoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        todoDatabase = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            TodoDatabase.NAME
        ).build()
    }
}