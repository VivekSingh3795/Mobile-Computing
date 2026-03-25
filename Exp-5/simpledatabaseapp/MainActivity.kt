package com.example.simpledatabaseapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Link the UI elements from activity_main.xml
        val inputField = findViewById<EditText>(R.id.editTextName)
        val saveBtn = findViewById<Button>(R.id.btnSave)
        val clearBtn = findViewById<Button>(R.id.btnClear)
        val displayArea = findViewById<TextView>(R.id.textViewData)

        // 2. Get the database instance
        val db = AppDatabase.getDatabase(this)
        val userDao = db.userDao()

        // 3. Load existing data immediately when app opens
        refreshDisplay(userDao, displayArea)

        // 4. Logic for the SAVE button
        saveBtn.setOnClickListener {
            val name = inputField.text.toString().trim()

            if (name.isNotEmpty()) {
                // Database operations must run in a Coroutine (background)
                lifecycleScope.launch {
                    userDao.insert(User(name = name))
                    inputField.text.clear() // Clear the input box
                    refreshDisplay(userDao, displayArea) // Update the list
                    Toast.makeText(this@MainActivity, "Saved!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }

        // 5. Logic for the CLEAR ALL button
        clearBtn.setOnClickListener {
            lifecycleScope.launch {
                userDao.deleteAll() // Wipe the table
                refreshDisplay(userDao, displayArea) // Update the list (will show empty)
                Toast.makeText(this@MainActivity, "All data deleted!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Helper function to fetch data from DB and update the TextView
     */
    private fun refreshDisplay(userDao: UserDao, displayArea: TextView) {
        lifecycleScope.launch {
            val userList = userDao.getAllUsers()
            if (userList.isEmpty()) {
                displayArea.text = "No data found in database."
            } else {
                // Convert the list of User objects into a single string to display
                val sb = StringBuilder()
                userList.forEach { user ->
                    sb.append("ID: ${user.id} | Name: ${user.name}\n")
                }
                displayArea.text = sb.toString()
            }
        }
    }
}