package com.example.studentquizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.studentquizapp.Handler.ApiHandler
import com.example.studentquizapp.Handler.ServiceNetworkListener
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class QuestionActivity : AppCompatActivity() {

    lateinit var jsonArray: JSONArray
    lateinit var question_count : TextView
    lateinit var question : TextView
    lateinit var option1 : TextView
    lateinit var option2 : TextView
    lateinit var option3 : TextView
    lateinit var option4 : TextView
    lateinit var loading : TextView
    lateinit var queslayout : ConstraintLayout
    lateinit var chronometer :  Chronometer
    lateinit var submit : Button
    lateinit var correctAnswer: TextView
    var getQuestionNumber : Int = 0
    var clicked : Boolean = false
    var correctAnswerCount : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_activity)

        initializeView()
        getApiData()
    }

    private fun initializeView() {
        question_count = findViewById(R.id.question_count)
        question = findViewById(R.id.question)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)
        chronometer = findViewById(R.id.chrono_timer)
        submit = findViewById(R.id.submit_button)
        correctAnswer = findViewById(R.id.correct_ans)
        loading = findViewById(R.id.loading)
        queslayout = findViewById(R.id.ques_layout)
        loading.visibility = View.VISIBLE
        queslayout.visibility = View.GONE
    }

    private fun getApiData() {
        try {
            ApiHandler.getAyncNetworkCall(getString(R.string.api_url), this, object : ServiceNetworkListener {
                override fun onError(message: String) {
                    Log.d("*&*QuizError: ",message)
                    loading.setText("Try Again Later..")
                }

                override fun onResponse(response: JSONObject) {
                    try {
                    var responseString = response.toString()
                    var obj = JSONObject(responseString)
                    jsonArray = obj.getJSONArray("results")
                        var result = jsonArray.getJSONObject(getQuestionNumber)
                        if (result != null) {
                            setValuetoField(getQuestionNumber)
                            chronometer.start()
                        } else {
                            Toast.makeText(this@QuestionActivity,"Please Try Again Later...",Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: IOException) {
                        Log.d("*&*QuizResExcp: ", e.toString())
                    }
                }
            })
        } catch (e: Exception) {
            Log.d("*&*QuizExcep: ", e.toString())
        }
    }

    private fun setValuetoField(questionNumber: Int) {
        try {
            loading.visibility = View.GONE
            queslayout.visibility = View.VISIBLE
            var result = jsonArray.getJSONObject(getQuestionNumber)
            var count = getQuestionNumber +1
            option1.setBackgroundResource(android.R.color.transparent)
            option2.setBackgroundResource(android.R.color.transparent)
            option3.setBackgroundResource(android.R.color.transparent)
            option4.setBackgroundResource(android.R.color.transparent)
            question_count.setText("Question: "+count)
            question.setText(result.getString("question"))
            option1.setText(result.getString("correct_answer"))
            var incorrect_answer = result.getJSONArray("incorrect_answers")
            option2.setText(incorrect_answer.get(0).toString())
            option3.setText(incorrect_answer.get(1).toString())
            option4.setText(incorrect_answer.get(2).toString())
            clicked = false
        }catch (e: Exception){
            Log.d("*&*setFieldExp: ",e.toString())
        }
    }

    @SuppressLint("ResourceAsColor")
    fun option_1_click(view: View) {
        try{
        clicked = true
            var result = jsonArray.getJSONObject(getQuestionNumber)
        if(option1.text.equals(result.getString("correct_answer"))){
//            option1.setTextColor(R.color.green)
            option1.setBackgroundResource(R.color.green)
            correctAnswer.visibility = View.GONE
            correctAnswerCount = correctAnswerCount+1
        }else{
            option1.setBackgroundResource(R.color.red)
            correctAnswer.visibility = View.VISIBLE
            correctAnswer.setText("Correct Ans: "+result.getString("correct_answer"))
//                option1.setTextColor(R.color.red)
            }
        }catch (e: Exception){
            Log.d("*&*opt1: ",e.toString())
        }
    }
    @SuppressLint("ResourceAsColor")
    fun option_2_click(view: View) {
        try{
            clicked = true
            var result = jsonArray.getJSONObject(getQuestionNumber)
            if(option2.text.equals(result.getString("correct_answer"))){
                option2.setBackgroundResource(R.color.green)
                correctAnswer.visibility = View.GONE
                correctAnswerCount = correctAnswerCount+1
            }else {
                option2.setBackgroundResource(R.color.red)
                correctAnswer.visibility = View.VISIBLE
                correctAnswer.setText("Correct Ans: "+result.getString("correct_answer"))
            }
        }catch (e: Exception){
            Log.d("*&*opt2: ",e.toString())
        }
    }
    @SuppressLint("ResourceAsColor")
    fun option_3_click(view: View) {
        try{
            clicked = true
            var result = jsonArray.getJSONObject(getQuestionNumber)
            if(option3.text.equals(result.getString("correct_answer"))){
                option3.setBackgroundResource(R.color.green)
                correctAnswer.visibility = View.GONE
                correctAnswerCount = correctAnswerCount+1
            }else{
                option3.setBackgroundResource(R.color.red)
                correctAnswer.visibility = View.VISIBLE
                correctAnswer.setText("Correct Ans: "+result.getString("correct_answer"))
//                option3.setTextColor(R.color.red)
            }
        }catch (e: Exception){
            Log.d("*&*opt3: ",e.toString())
        }
    }
    @SuppressLint("ResourceAsColor")
    fun option_4_click(view: View) {
        try{
            clicked = true
            var result = jsonArray.getJSONObject(getQuestionNumber)
            if(option4.text.equals(result.getString("correct_answer"))) {
                option4.setBackgroundResource(R.color.green)
                correctAnswer.visibility = View.GONE
                correctAnswerCount = correctAnswerCount+1
            }else{
                option4.setBackgroundResource(R.color.red)
                correctAnswer.visibility = View.VISIBLE
                correctAnswer.setText("Correct Ans: "+result.getString("correct_answer"))
//                option4.setTextColor(R.color.red)
            }
        }catch (e: Exception){
            Log.d("*&*opt1: ",e.toString())
        }
    }
    fun nextQuestion(view: View) {
        correctAnswer.visibility = View.GONE
        try {
            if (clicked == true) {
                var result = jsonArray.getJSONObject(getQuestionNumber)
                if (getQuestionNumber.equals(9)) {
                    var elapsedMillis = SystemClock.elapsedRealtime() - chronometer.base
                    println("*&* Time Take to complete:  "+elapsedMillis)
                    var completeIntent = Intent(this, CompleteQuizActivity::class.java)
                    completeIntent.putExtra("correctanswercount", correctAnswerCount)
                    completeIntent.putExtra("counttimer",elapsedMillis)
                    startActivity(completeIntent)
                    finish()
                } else {
                    getQuestionNumber = getQuestionNumber + 1
                    setValuetoField(getQuestionNumber)
                }
            } else {
                Toast.makeText(this, "Please Choose answer before Submit..", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.d("*&*submitExp: ", e.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}