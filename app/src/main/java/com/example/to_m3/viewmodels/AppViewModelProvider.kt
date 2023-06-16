package com.example.to_m3.viewmodels

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.to_m3.ToDoApplication

object AppViewModelProvider {

    val Factory = viewModelFactory{
        initializer {
            ToDoFormViewModel(ToDoApplication().container.toDoRepository)
        }

    }
}