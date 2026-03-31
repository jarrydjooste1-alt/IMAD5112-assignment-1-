package com.example.socialspark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.util.Log

class MainActivity : AppCompatActivity() {
    /**
     * Social Sparks App
     * IMAD5112 Assignment
     * This app will give you suggested task to do depending on the time of day.
     * The use of if-statements is used for decision making.
     *
     * @author Jarryd Jooste
     * @version 1.0
     * @studentNumber ST10470158
     *
     */
    // Tag for logging
    companion object {
        private const val TAG = "SocialSparkApp"
    }

    // Declare UI elements
    private lateinit var etTimeOfDay: EditText
    private lateinit var btnGetSuggestion: Button
    private lateinit var btnReset: Button
    private lateinit var tvSuggestion: TextView
    private lateinit var tvTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: App started successfully")

        // Initialize UI elements
        initializeViews()

        // Set click listeners
        setupClickListeners()

        Log.d(TAG, "onCreate: UI initialized and listeners set up")
    }

    /**
     * Initialize all view elements
     */
    private fun initializeViews() {
        etTimeOfDay = findViewById(R.id.etTimeOfDay)
        btnGetSuggestion = findViewById(R.id.btnGetSuggestion)
        btnReset = findViewById(R.id.btnReset)
        tvSuggestion = findViewById(R.id.tvSuggestion)
        tvTitle = findViewById(R.id.tvTitle)

        Log.d(TAG, "initializeViews: All views initialized")
    }

    /**
     * Set up click listeners for buttons
     */
    private fun setupClickListeners() {
        btnGetSuggestion.setOnClickListener {
            Log.d(TAG, "Get Suggestion button clicked")
            getSocialSuggestion()
        }

        btnReset.setOnClickListener {
            Log.d(TAG, "Reset button clicked")
            resetApp()
        }
    }

    /**
     * This is the are where you get social suggestion based on the time of day input
     */
    private fun getSocialSuggestion() {
        // Get user input and convert to lowercase for easier comparison
        val timeInput = etTimeOfDay.text.toString().trim().lowercase()

        Log.d(TAG, "getSocialSuggestion: User entered '$timeInput'")

        // Check if input is empty
        if (timeInput.isEmpty()) {
            Log.w(TAG, "Empty input detected")
            showErrorMessage("Please enter a time of day! ")
            return
        }

        // Use when expression (similar to switch) to determine the suggestion
        val suggestion = when (timeInput) {
            "morning" -> {
                Log.d(TAG, "Morning time selected")
                " Good morning spark: Send a 'Good morning' text to a family member!"
            }
            "mid-morning", "midmorning" -> {
                Log.d(TAG, "Mid-morning time selected")
                " Mid-morning spark: Reach out to a colleague with a quick 'Thank you'!"
            }
            "afternoon" -> {
                Log.d(TAG, "Afternoon time selected")
                " Afternoon spark: Share a funny meme or interesting link with a friend!"
            }
            "afternoon snack time", "snack time", "snack" -> {
                Log.d(TAG, "Snack time selected")
                " Afternoon Snack Time spark: Send a quick 'thinking of you' message!"
            }
            "dinner", "evening" -> {
                Log.d(TAG, "Dinner/Evening time selected")
                " Dinner spark: Call a friend or relative for a 5-minute catch-up!"
            }
            "after dinner", "night", "evening after dinner" -> {
                Log.d(TAG, "After dinner/Night time selected")
                " After Dinner/Night spark: Leave a thoughtful comment on a friend's post!"
            }
            else -> {
                Log.w(TAG, "Invalid time input: $timeInput")
                handleInvalidInput(timeInput)
                return
            }
        }

        // Display the suggestion
        tvSuggestion.text = suggestion
        tvSuggestion.visibility = TextView.VISIBLE

        // Show success toast
        Toast.makeText(this, "Spark suggestion ready! ", Toast.LENGTH_SHORT).show()

        Log.d(TAG, "Suggestion displayed successfully for input: $timeInput")
    }

    /**
     * Handle invalid input with user-friendly feedback. Meaning that if you make a mistake or zsomething a suggestion will pop up, telling you how to fix it
     */
    private fun handleInvalidInput(input: String): String {
        val errorMessage = when {
            input.contains("morning") && input != "morning" ->
                "Try 'Morning' for a morning spark! "
            input.contains("afternoon") && input != "afternoon" ->
                "Try 'Afternoon' for an afternoon spark! "
            input.contains("dinner") || input.contains("evening") ->
                "Try 'Dinner' or 'Evening' for dinner spark! "
            else -> "Try: Morning, Mid-morning, Afternoon, Snack Time, Dinner, or Night! "
        }

        showErrorMessage(errorMessage)
        return "" // Return empty string for when expression
    }

    /**
     * Show error message to user
     * If you put invalid time of day then a error will occur
     */
    private fun showErrorMessage(message: String) {
        tvSuggestion.text = message
        tvSuggestion.visibility = TextView.VISIBLE
        Toast.makeText(this, "Invalid input! Please try again. ", Toast.LENGTH_SHORT).show()
        Log.e(TAG, "Error: $message")
    }

    /**
     * Reset all fields and clear the suggestion
     * This is the reset button so if you click this button then all fields will be cleared and reset
     */
    private fun resetApp() {
        etTimeOfDay.text.clear()
        tvSuggestion.text = "Your social spark will appear here! "
        tvSuggestion.visibility = TextView.VISIBLE
        etTimeOfDay.requestFocus()

        Toast.makeText(this, "Reset complete! Ready for new input 🔄", Toast.LENGTH_SHORT).show()

        Log.d(TAG, "resetApp: All fields cleared and reset")
    }
}