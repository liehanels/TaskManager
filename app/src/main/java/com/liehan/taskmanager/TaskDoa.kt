package com.liehan.taskmanager

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
// Create the DAO Interface
@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>
}