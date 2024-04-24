package com.example.firstapplication

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = findViewById<EditText>(R.id.edit_text_email)
        val password = findViewById<EditText>(R.id.edit_text_password)
        val button = findViewById<Button>(R.id.button_login)
        val errorMessage = findViewById<TextView>(R.id.text_view_error_message)


        button.setOnClickListener {
            var emailValidate = false
            var passwordValidate = false
            val submittedEmail:String = email.text.toString()
            val submittedPassword:String = password.text.toString()

            emailValidate =
                submittedEmail.toString().contains('@')
                 && submittedEmail.toString().contains('.')

            Log.d("MainActivity","${submittedEmail.toString()}: ${emailValidate.toString()}")
            passwordValidate = validatePassword((submittedPassword.toString()))

             if (!emailValidate) {
                errorMessage.text = getString(R.string.text_view_error_message_email)
            } else if(!passwordValidate) {
                errorMessage.text = getString(R.string.text_view_error_message_passwrod)
            }
            else {
                errorMessage.text = ""
            }
        }
    }

    fun validatePassword(pass: String): Boolean {
        val myPassword = pass.trim()
        var validateCapital = false
        var validateSmallLetter = false
        var specialCar = false

        if (myPassword.length < 8) {
            return false
        }
        pass.forEach {
            if (it.isLetter() && it.isLowerCase()) {
                validateSmallLetter = true
            } else if (it.isLetter() && it.isUpperCase()) {
                validateCapital = true
            } else if (!it.isLetterOrDigit() && !it.isWhitespace()) {
                specialCar = true
            }
        }
        return validateCapital && validateSmallLetter && specialCar
    }
}
