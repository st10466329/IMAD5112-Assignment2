package com.example.a2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Score : AppCompatActivity() {

    private lateinit var scoreText: TextView
    private lateinit var reviewButton: Button
    private lateinit var feedbackText: TextView
    private lateinit var exitButton: Button
    private lateinit var retryButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        scoreText = findViewById(R.id.scoreText)
        feedbackText = findViewById(R.id.feedbackTextt)
        reviewButton = findViewById(R.id.reviewButton)
        exitButton = findViewById(R.id.exitButton)
        retryButton = findViewById(R.id.retryBtn) // Make sure this exists in activity_score.xml

        val score = intent.getIntExtra("score", 0)
        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        scoreText.text = "You scored $score out of 5"

        feedbackText.text = when {
            score >= 4 -> "Great job!"
            score >= 2 -> "Keep practising!"
            else -> "Don’t give up — try again!"
        }

        // Show retry button only if score is less than 3
        if (score < 3) {
            retryButton.visibility = Button.VISIBLE
        } else {
            retryButton.visibility = Button.GONE
        }

        retryButton.setOnClickListener {
            // Restart the quiz (adjust MainActivity if needed)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        reviewButton.setOnClickListener {
            showReviewDialog(questions, answers)
        }

        exitButton.setOnClickListener {
            finishAffinity() // Terminates the app
        }
    }

    private fun showReviewDialog(questions: Array<String>?, answers: BooleanArray?) {
        if (questions == null || answers == null) {
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Unable to load review data.")
                .setPositiveButton("OK", null)
                .show()
            return
        }

        val reviewMessage = StringBuilder()
        for (i in questions.indices) {
            reviewMessage.append("${i + 1}. ${questions[i]}\nAnswer: ${if (answers[i]) "True" else "False"}\n\n")
        }

        AlertDialog.Builder(this)
            .setTitle("Review Answers")
            .setMessage(reviewMessage.toString())
            .setPositiveButton("OK", null)
            .show()
    }
}
