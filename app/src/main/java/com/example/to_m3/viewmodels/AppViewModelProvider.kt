package com.example.to_m3.viewmodels

import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.to_m3.ToDoApplication
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            ToDoFormViewModel(todoApplication().container.toDoRepository)
        }

        initializer {
            ToDoViewModel(toDoRepository = todoApplication().container.toDoRepository)
        }

    }
}

fun CreationExtras.todoApplication(): ToDoApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ToDoApplication)