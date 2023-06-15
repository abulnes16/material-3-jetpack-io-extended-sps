package com.example.to_m3.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.to_m3.data.models.ToDo
import kotlinx.coroutines.flow.Flow


@Dao
interface ToDoDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: ToDo)

    @Update
    suspend fun update(todo: ToDo)

    @Delete
    suspend fun delete(todo: ToDo)

    @Query("SELECT * from todos")
    fun getAll(): Flow<List<ToDo>>

    @Query("SELECT * from todos WHERE id = :id")
    fun getTodo(id: Int): Flow<ToDo>

}