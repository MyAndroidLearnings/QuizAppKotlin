package com.example.studentquizapp.QuizModel

import kotlinx.serialization.*

data class QuizModelClass (
        @SerialName("response_code")
        val response_code: Long,

        val results: List<Result>
)

data class Result (
        val category: String,
        val type: String,
        val difficulty: String,
        val question: String,

        @SerialName("correct_answer")
        val correct_answer: String,

        @SerialName("incorrect_answers")
        val incorrect_answers: List<String>)
