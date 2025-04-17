package com.liehan.taskmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //code start here
        val username = intent.getStringExtra("username")
        if (username == null) {
            finish()
            return
        }
        val tvMenuTitle = findViewById<TextView>(R.id.tvMenuTitle)
        val btnAddTask = findViewById<Button>(R.id.btnAddTask)
        val btnViewTasks = findViewById<Button>(R.id.btnViewTasks)
        val btnSearchTasks = findViewById<Button>(R.id.btnSearchTasks)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        tvMenuTitle.text = "Welcome $username"
        btnAddTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
        btnViewTasks.setOnClickListener {
            val intent = Intent(this, ViewTasksActivity::class.java)
            startActivity(intent)
        }
        /*
        btnSearchTasks.setOnClickListener {
            val intent = Intent(this, SearchTasksActivity::class.java)
            startActivity(intent)
        }*/
        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}