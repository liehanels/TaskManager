package com.liehan.taskmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //code starts here
        val Username = "Lee"
        val Password = "Qwerty1"

        val edtUsername = findViewById<EditText>(R.id.edtUsername)
        val pwPassword = findViewById<EditText>(R.id.pwPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            var username = edtUsername.text.toString()
            var password = pwPassword.text.toString()
            if (username == Username && password == Password) {
                val intent = Intent(this, MenuActivity::class.java)
                    .putExtra("username", username)
                startActivity(intent)
                finish()
            } else if (username != Username) {
                edtUsername.error = "Invalid username"
                Toast.makeText(this, "Invalid username", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "Invalid username")
            } else {
                pwPassword.error = "Invalid password"
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "Invalid password")
            }
        }
    }
}