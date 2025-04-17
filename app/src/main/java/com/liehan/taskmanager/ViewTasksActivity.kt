package com.liehan.taskmanager

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewTasksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_tasks)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //code starts here
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val btnBackToMenu = findViewById<Button>(R.id.btnBackToMenu)
        val database = DatabaseProvider.getDatabase(this)
        val taskDao = database.taskDao()
        CoroutineScope(Dispatchers.IO).launch {
            val tasks = taskDao.getAllTasks()
            Log.d("ViewTasksActivity", "Tasks: $tasks")
            withContext(Dispatchers.Main) {
                recyclerView.layoutManager = LinearLayoutManager(this@ViewTasksActivity)
                recyclerView.adapter = TaskAdapter(tasks)
                Log.d("ViewTasksActivity", "Adapter set with ${tasks.size} tasks")
            }
        }
        btnBackToMenu.setOnClickListener {
            finish()
        }
    }
}