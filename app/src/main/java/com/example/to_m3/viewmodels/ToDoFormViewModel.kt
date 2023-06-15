package com.example.to_m3.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_m3.data.models.ToDo
import com.example.to_m3.data.models.ToDoFormEvent
import com.example.to_m3.data.models.ToDoFormState
import com.example.to_m3.data.repository.ToDoRepository
import kotlinx.coroutines.launch
import java.util.Date

const val TAG = "[ToDoFormViewModel]"

class ToDoFormViewModel(
    private val toDoRepository: ToDoRepository,
    private val todoId: Int? = null
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

    suspend fun onSaveToDo() {
        // We validate that the ToDo is completed
        if (!validateToDo()) {
            return
        }


        if (todoId != null) {
            updateTodo()
        } else {
            createTodo()
        }
    }

    private fun validateToDo(): Boolean {
        val (_, title, category, description) = state
        if (title.isBlank() || category.isBlank() || description.isBlank()) {
            return false
        }

        return true
    }

    private suspend  fun createTodo() {
        val (_, title, category, description) = state
        val newTodo = ToDo(
            id = 1,
            title = title,
            description = description,
            category = category,
            creationDate =  "Hoy",
            isCompleted = false
        )

        // TODO: Save on Database
        Log.d(TAG, newTodo.toString())



    }

    private fun updateTodo() {

    }

}