package com.example.tarea2manosalaobraconandroidstudio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputFilter
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)

    val numberEditText = findViewById<TextInputEditText>(R.id.number_edit_text)
    val getNumberButton = findViewById<MaterialButton>(R.id.get_number_button)

    numberEditText.filters = arrayOf(InputFilter { source, _, _, dest, _, _ ->
      try {
        val input = (dest.toString() + source.toString()).toInt()
        if (input <= 100) { // Set your maximum value here
          null // Accept the input
        } else {
          "" // Reject the input
        }
      } catch (e: NumberFormatException) {
        "" // Reject the input if it's not a valid number
      }
    })

    fun displayResult(qualification: String, emoji: String) {
      val resultText = findViewById<MaterialTextView>(R.id.result_text)
      resultText.text = "$emoji obtuviste una $qualification."
    }

    fun displayPoints(points: Int) {
      val pointsText = findViewById<MaterialTextView>(R.id.points_text)
      pointsText.text = "Puntuación: $points"
    }

    getNumberButton.setOnClickListener {
      val enteredText = numberEditText.text.toString()
      val resultText = findViewById<MaterialTextView>(R.id.result_text)

      if (enteredText.isNotEmpty()) {
        val enteredNumber = enteredText.toInt()
        if (enteredNumber < 70) {
          displayResult("F", "Lo siento \uD83D\uDE13")
        } else if (enteredNumber < 80) {
          displayResult("C", "En hora buena \uD83D\uDE06")
        } else if (enteredNumber < 90) {
          displayResult("B", "Felicitaciones \uD83E\uDD73")
        } else {
          displayResult("A", "Asombroso! \uD83E\uDD2F")
        }

        displayPoints(enteredNumber)

        val nameText = findViewById<MaterialTextView>(R.id.name_text)
        val params = nameText.layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = 80 // Set your desired width in pixels
        nameText.layoutParams = params

      } else {
        resultText.text = "Por favor ingresa una nota \uD83D\uDE4F."
      }

      numberEditText.text?.clear()
    }

    val websiteButton = findViewById<MaterialButton>(R.id.website_button)
    websiteButton.setOnClickListener{
      val url = "https://adry-dev-website.netlify.app/"
      val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
      startActivity(intent)
    }

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
  }
}