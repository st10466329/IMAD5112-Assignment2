package com.example.a2

import android.os.Bundle
import android.content.Intent
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Questions : AppCompatActivity() {



        private val questions = arrayOf(
            "Mamelodi Sundowns is thw best in the world",
            "The Eiffel Tower is in Germany",
            "Water boils at 100°C",
            "The moon is a star",
            "Java is a type of OS"
        )

        private val answers = booleanArrayOf(true, false, true, false, false)

        private var currentIndex = 0
        private var score = 0

        private lateinit var questionText: TextView
        private lateinit var feedbackText: TextView
        private lateinit var trueButton: Button
        private lateinit var falseButton: Button
        private lateinit var nextButton: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_questions)

            questionText = findViewById(R.id.QuestionText)
            feedbackText = findViewById(R.id.feedBack)
            trueButton = findViewById(R.id.trueBtn)
            falseButton = findViewById(R.id.falseBtn)
            nextButton = findViewById(R.id.nextBtn)

            loadQuestion()

            trueButton.setOnClickListener {
                checkAnswer(true)
            }

            falseButton.setOnClickListener {
                checkAnswer(false)
            }

            nextButton.setOnClickListener {
                currentIndex++
                if (currentIndex < questions.size) {
                    loadQuestion()
                } else {
                    // Ensure questions are cast explicitly as StringArray
                    val intent = Intent(this, Score::class.java)
                    intent.putExtra("score", score)
                    intent.putExtra("questions", questions as Array<String>)
                    intent.putExtra("answers", answers)
                    startActivity(intent)
                    finish()
                }
            }
        }

        private fun loadQuestion() {
            // Prevent crashing if index goes out of range
            if (currentIndex < questions.size) {
                questionText.text = questions[currentIndex]
                feedbackText.text = ""

                // Re-enable buttons for new question
                trueButton.isEnabled = true
                falseButton.isEnabled = true
            }
        }

        private fun checkAnswer(userAnswer: Boolean) {
            val correct = answers[currentIndex]
            if (userAnswer == correct) {
                feedbackText.text = "Correct!"
                score++
            } else {
                feedbackText.text = "Incorrect"
            }

            // Disable buttons after answering
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }
    }




