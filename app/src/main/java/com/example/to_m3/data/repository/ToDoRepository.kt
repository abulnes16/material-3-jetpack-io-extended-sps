package com.example.to_m3.data.repository

import com.example.to_m3.data.database.ToDoDAO
import com.example.to_m3.data.models.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    fun getAllTodos(): Flow<List<ToDo>>

    fun getToDo(id: Int): Flow<ToDo?>

    suspend fun insertToDo(todo: ToDo)
    suspend fun updateToDo(todo: ToDo)
    suspend fun deleteToDo(todo: ToDo)
}

class OfflineToDoRepository(private val toDoDAO: ToDoDAO) : ToDoRepository {
    override fun getAllTodos(): Flow<List<ToDo>> = toDoDAO.getAll()

    override fun getToDo(id: Int): Flow<ToDo?> = toDoDAO.getTodo(id)

    override suspend fun insertToDo(todo: ToDo) = toDoDAO.insert(todo)

    override suspend fun updateToDo(todo: ToDo) = toDoDAO.update(todo)

    override suspend fun deleteToDo(todo: ToDo) = toDoDAO.delete(todo)

}