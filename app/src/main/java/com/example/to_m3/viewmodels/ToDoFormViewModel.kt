package com.example.to_m3.viewmodels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_m3.data.models.ToDo
import com.example.to_m3.data.models.ToDoFormEvent
import com.example.to_m3.data.models.ToDoFormState
import com.example.to_m3.data.repository.ToDoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date

const val TAG = "[ToDoFormViewModel]"

class ToDoFormViewModel(
    private val toDoRepository: ToDoRepository,
) : ViewModel() {
    var state by mutableStateOf(
        ToDoFormState(isModalOpen = false, title = "", description = "", category = "")
    )
        private set


    fun onFormChange(event: ToDoFormEvent) {
        state = when (event) {
            is ToDoFormEvent.OnOpenModalEvent -> state.copy(isModalOpen = event.isModalOpen)
            is ToDoFormEvent.OnTitleEvent -> state.copy(title = event.title)
            is ToDoFormEvent.OnDescriptionEvent -> state.copy(description = event.description)
            is ToDoFormEvent.OnCategoryEvent -> state.copy(category = event.category)
        }
    }

    fun onSaveToDo(onError: () -> Unit, onSuccess: () -> Unit, todo: ToDo? = null) {
        // We validate that the ToDo is completed
        if (!validateToDo()) {
            return
        }
        Log.d(TAG, todo?.toString() ?: "")
        if (todo != null) {
            updateTodo(onError, onSuccess, todo)
        } else {
            createTodo(onError, onSuccess)
        }
    }

    private fun validateToDo(): Boolean {
        val (_, title, category, description) = state
        if (title.isBlank() || category.isBlank() || description.isBlank()) {
            return false
        }

        return true
    }

    private fun createTodo(onError: () -> Unit, onSuccess: () -> Unit) {
        val (_, title, category, description) = state
        val newTodo = ToDo(
            title = title,
            description = description,
            category = category,
            creationDate = Date().toString(),
            isCompleted = false
        )


        viewModelScope.launch {
            try {
                toDoRepository.insertToDo(newTodo)
                state = state.copy(isModalOpen = false)
                onSuccess()
            } catch (error: Exception) {
                Log.e(TAG, error.message ?: "Error creating ToDo")
                onError()
            }
        }
    }

    private fun updateTodo(onError: () -> Unit, onSuccess: () -> Unit, todo: ToDo) {
        viewModelScope.launch {
            try {
                val updatedTodo = todo.copy(
                    title = state.title,
                    description = state.description,
                    category = state.category
                )
                Log.d(TAG, updatedTodo.toString())
                toDoRepository.updateToDo(updatedTodo)
                state = state.copy(isModalOpen = false)
                onSuccess()
            } catch (error: Exception) {
                Log.d(TAG, error.message ?: "Error creating ToDo")
                onError()
            }
        }
    }

    fun setupCurrentTodo(todo: ToDo) {
        state = state.copy(
            title = todo.title,
            category = todo.category,
            description = todo.description
        )
    }


}