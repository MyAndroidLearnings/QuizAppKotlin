package com.example.studentquizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CompleteQuizActivity : AppCompatActivity() {

    lateinit var score : TextView
    lateinit var timeTaken : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.complete_quiz_activity)

        score = findViewById(R.id.score_text)
        timeTaken = findViewById(R.id.time_text)

        try {
            var intentValue = intent
            var scoredValue = intentValue.getIntExtra("correctanswercount", 0)
            var timer = intentValue.getLongExtra("counttimer",0)
            var sec = (timer/1000)%60
            var min = (timer/1000)/60
            score.setText("Your Score: " + scoredValue)
            if(min >0) {
                timeTaken.setText("Time Taken: " + min + " mins"+ sec +" secs")
            }else{
                timeTaken.setText("Time Taken: " + sec+" secs")
            }
        }catch (e: Exception){
            Log.d("*&*completeExp: ",e.toString())
        }
    }

    fun homePage(view: View) {
        var homeIntent = Intent(this,MainActivity::class.java)
        startActivity(homeIntent)
        finish()
    }
}