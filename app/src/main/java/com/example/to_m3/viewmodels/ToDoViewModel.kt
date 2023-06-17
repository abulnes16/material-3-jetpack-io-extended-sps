package com.example.to_m3.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_m3.data.models.ToDoState
import com.example.to_m3.data.repository.ToDoRepository
import kotlinx.coroutines.launch

const val TAG_TODO: String = "[ToDoViewModel]"

class ToDoViewModel(private val toDoRepository: ToDoRepository) :
    ViewModel() {

    var state by mutableStateOf(ToDoState(todos = listOf(), currentTodo = null, loading = true))
        private set

    init {
        getTodos()
    }


    fun deleteTodo(onError: () -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                toDoRepository.deleteToDo(state.currentTodo!!)
                onSuccess()
            } catch (error: Exception) {
                Log.e(TAG_TODO, error.toString())
                onError()
            }
        }
    }

    fun updateCompletion(completion: Boolean) {
        viewModelScope.launch {
            try {
                val updatedTodo = state.currentTodo?.copy(
                    isCompleted = completion
                )
                toDoRepository.updateToDo(updatedTodo!!)
                state = state.copy(
                    currentTodo = updatedTodo
                )
            } catch (error: Exception) {
                Log.e(TAG_TODO, error.toString())

            }
        }
    }

    fun getTodoById(todoId: Int) {

        viewModelScope.launch {
            try {
                toDoRepository.getToDo(todoId).collect {
                    state = state.copy(
                        currentTodo = it,
                        loading = false
                    )
                }
            } catch (error: Exception) {
                Log.e(TAG_TODO, error.toString())
            }
        }

    }

    private fun getTodos() {
        viewModelScope.launch {
            try {
                toDoRepository.getAllTodos().collect {
                    state = state.copy(
                        todos = it,
                        loading = false
                    )
                }
            } catch (error: Exception) {
                Log.e(TAG_TODO, error.toString())
            }
        }
    }


}