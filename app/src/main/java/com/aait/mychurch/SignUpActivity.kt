package com.aait.mychurch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {

    val firebaseAuth = FirebaseAuth.getInstance()
    val user: FirebaseUser? = firebaseAuth.currentUser

    lateinit var emailSU: String
    lateinit var passwordSU: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)






        loginLink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        signupBtn.setOnClickListener {

            if (firstNameReg.text.toString().trim().isEmpty()) {
                firstNameReg.error = "First Name is required"
                firstNameReg.requestFocus()
                return@setOnClickListener
            }
            if (lastNameReg.text.toString().trim().isEmpty()) {
                lastNameReg.error = "Last Name is required"
                lastNameReg.requestFocus()
                return@setOnClickListener
            }
            if (emailReg.text.toString().trim().isEmpty()) {
                emailReg.error = "Email is required"
                emailReg.requestFocus()
                return@setOnClickListener
            }
            if (phoneReg.text.toString().trim().isEmpty()) {
                phoneReg.error = "Phone No is required"
                phoneReg.requestFocus()
                return@setOnClickListener
            }
            if (passwordReg.text.toString().trim().isEmpty()) {
                passwordReg.error = "Password is required"
                passwordReg.requestFocus()
                return@setOnClickListener
            }
            if (confirmPwReg.text.toString().trim().isEmpty()) {
                confirmPwReg.error = "Password is required"
                confirmPwReg.requestFocus()
                return@setOnClickListener
            }
            if (passwordReg.text.toString().trim() != confirmPwReg.text.toString().trim()) {
                confirmPwReg.error = "Password must match"
                confirmPwReg.requestFocus()
                return@setOnClickListener
            }
            emailSU = emailReg.text.toString().trim()
            passwordSU = passwordReg.text.toString().trim()



            firebaseAuth.createUserWithEmailAndPassword(emailSU, passwordSU)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Created account successfully!", Toast.LENGTH_LONG)
                            .show()


                        sendEmailVerification()
//                    startActivity()
                        finish()
                    } else {
                        Toast.makeText(this, "Failed to authenticate", Toast.LENGTH_LONG).show()
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()

                        Log.i("TAG", task.exception?.message +"")
                        task.exception?.localizedMessage?.let { it1 -> Log.i("TAG", it1) }

                    }
                }


        }


    }

    private fun sendEmailVerification() {
        user?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "email sent to $emailSU", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

