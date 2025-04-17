package com.liehan.taskmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_task)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get UI elements
        val edtTaskTitle = findViewById<EditText>(R.id.edtTaskTitle)
        val edtDescription = findViewById<EditText>(R.id.edtDescription)
        val rdgPriority = findViewById<RadioGroup>(R.id.rdgPriority)
        val edtDueDate = findViewById<EditText>(R.id.edtDueDate)
        val btnConfirmAddTask = findViewById<Button>(R.id.btnConfirmAddTask)
        val btnCancelAddTask = findViewById<Button>(R.id.btnCancelAddTask)

        // Get the database and DAO
        val database = DatabaseProvider.getDatabase(this)
        val taskDao = database.taskDao()

        btnConfirmAddTask.setOnClickListener {
            val title = edtTaskTitle.text.toString()
            val description = edtDescription.text.toString()
            val priority = when (rdgPriority.checkedRadioButtonId) {
                R.id.rbtnLow -> "Low"
                R.id.rbtnMed -> "Medium"
                R.id.rbtnHigh -> "High"
                else -> null
            }
            val dueDate = edtDueDate.text.toString()

            if (title.isEmpty() || description.isEmpty() || priority == null || dueDate.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val task = Task(title = title, description = description, priority = priority, dueDate = dueDate)
                CoroutineScope(Dispatchers.IO).launch {
                    taskDao.insertTask(task)
                    // You can switch to the Main thread for UI updates after the insertion if needed.
                    runOnUiThread {
                        Toast.makeText(this@AddTaskActivity, "Task added successfully", Toast.LENGTH_SHORT).show()
                        edtTaskTitle.text.clear()
                        edtDescription.text.clear()
                        rdgPriority.clearCheck()
                        edtDueDate.text.clear()
                        finish()
                    }
                }

            }
        }

        btnCancelAddTask.setOnClickListener {
            finish()
        }
    }
}