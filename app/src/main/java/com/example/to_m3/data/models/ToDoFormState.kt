package com.example.to_m3.data.models

data class ToDoFormState(
    var isModalOpen: Boolean,
    var title: String,
    var category: String,
    var description: String,
)

data class ToDoState(
    val todos: List<ToDo>,
    val currentTodo: ToDo?,
    val loading: Boolean,
)
