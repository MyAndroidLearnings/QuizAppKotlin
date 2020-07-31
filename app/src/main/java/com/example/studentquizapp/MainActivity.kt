package com.example.studentquizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentquizapp.R.layout

class MainActivity : AppCompatActivity() {

    lateinit var userName : EditText
    lateinit var name : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userName = findViewById(R.id.user_name_edit_text)
        name = userName.text.toString()

    }

    fun startQuiz(view: View) {
        var questionIntent = Intent(this,QuestionActivity::class.java)
        startActivity(questionIntent)
        finish()
    }
}