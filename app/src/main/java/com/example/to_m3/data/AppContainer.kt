package com.example.to_m3.data

import android.content.Context
import com.example.to_m3.data.database.ToDoDatabase
import com.example.to_m3.data.repository.OfflineToDoRepository
import com.example.to_m3.data.repository.ToDoRepository

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val toDoRepository: ToDoRepository
}


class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ToDoRepository]
     */
    override val toDoRepository: ToDoRepository by lazy {
        OfflineToDoRepository(ToDoDatabase.getDatabase(context).toDoDao())
    }
}