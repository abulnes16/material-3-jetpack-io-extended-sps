package com.example.to_m3.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todos")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var description: String,
    var category: String,
    var isCompleted: Boolean,
    val creationDate: Date
)


val mockTodos = listOf<ToDo>(
    ToDo(
        id = 1,
        title = "Do dishes",
        description = "Lorem ipsum ifactus jkasakdsa",
        category = "home",
        isCompleted = false,
        creationDate = Date()
    ),
    ToDo(
        id = 2,
        title = "Do Laundry",
        description = "Lorem ipsum ifactus jkasakdsa",
        category = "home",
        isCompleted = false,
        creationDate = Date()
    ),
    ToDo(
        id = 3,
        title = "Make the presentation",
        description = "Lorem ipsum ifactus jkasakdsa",
        category = "work",
        isCompleted = false,
        creationDate = Date()
    ),
    ToDo(
        id = 4,
        title = "Finish the app",
        description = "Lorem ipsum ifactus jkasakdsa",
        category = "gdg",
        isCompleted = false,
        creationDate = Date()
    ),
    ToDo(
        id = 5,
        title = "Go to therapy",
        description = "Lorem ipsum ifactus jkasakdsa",
        category = "personal",
        isCompleted = false,
        creationDate = Date()
    ),
)
