package com.liehan.taskmanager

import androidx.room.Entity
import androidx.room.PrimaryKey

// Define the Entity
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val priority: String,
    val dueDate: String
)