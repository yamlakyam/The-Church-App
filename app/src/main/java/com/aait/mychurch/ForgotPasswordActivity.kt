package com.aait.mychurch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var emailFP: String
    val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        sendLinkBtn.setOnClickListener {
            if (emailForgot.text.toString().trim().isEmpty()) {
                emailForgot.error = "Email is required"
                emailForgot.requestFocus()
                return@setOnClickListener
            }
            emailFP = emailForgot.text.toString().trim()

            firebaseAuth.sendPasswordResetEmail(emailFP).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Reset email sent to $emailFP successfully!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "Error sending email to $emailFP !",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }
}