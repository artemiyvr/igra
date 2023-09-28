package com.example.myapplication

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var guessEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var resultTextView: TextView

    private val countries = arrayOf("Франция", "Беларусь", "Италия", "Россия", "США", "Украина", "ОАЭ", "Канада", "Армения", "Австралия", "Германия", "Испания", "Казахстан", "Польша", "Венеция")
    private val images = arrayOf("france.jpg", "belarus.jpg", "italy.jpg", "russia.jpg", "usa.jpg", "ukraine.jpg", "uae.jpg", "canada.jpg", "armenia.jpg", "australia.jpg", "germany.jpg", "ispaniya.jpg", "kaz.jpg", "polsha.jpg", "venezia.jpg")
    private var currentCountryIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        guessEditText = findViewById(R.id.guessEditText)
        submitButton = findViewById(R.id.submitBtn)
        resultTextView = findViewById(R.id.resultTextView)

        submitButton.setOnClickListener {
            checkGuess()
        }

        loadCountryImage()
    }

    private fun loadCountryImage() {
        val imagePath = "file:///android_asset/${images[currentCountryIndex]}"
        val drawable = Drawable.createFromStream(assets.open(images[currentCountryIndex]), null)
        imageView.setImageDrawable(drawable)
    }

    private fun checkGuess() {
        val guess = guessEditText.text.toString().trim()
        val correctCountry = countries[currentCountryIndex]

        if (guess.equals(correctCountry, ignoreCase = true)) {
            resultTextView.text = "Правильно!"
        } else {
            resultTextView.text = "Неправильно! Это - $correctCountry"
        }

        guessEditText.setText("")
        guessEditText.clearFocus()
        resultTextView.visibility = View.VISIBLE

        if (currentCountryIndex < countries.size - 1) {
            currentCountryIndex++
            loadCountryImage()
        } else {
            submitButton.isEnabled = false
            guessEditText.isEnabled = false
        }
    }
}
