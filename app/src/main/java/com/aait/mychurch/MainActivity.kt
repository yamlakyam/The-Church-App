package com.aait.mychurch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var emailLI:String
    lateinit var passwordLI:String
    val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signupLink.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }

        forgotPwLink.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            if(emailLogin.text.toString().trim().isEmpty()){
                emailLogin.error="Email is required"
                emailLogin.requestFocus()
                return@setOnClickListener
            }
            if(passwordLogin.text.toString().trim().isEmpty()){
                passwordLogin.error="Email is required"
                passwordLogin.requestFocus()
                return@setOnClickListener
            }

            signInUser()

//            val intent = Intent(this, )
//            startActivity(intent)
        }

    }

    private fun signInUser() {
        emailLI=emailLogin.text.toString().trim()
        passwordLI=passwordLogin.text.toString().trim()

        if(emailLI.isNotEmpty()&& passwordLI.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(emailLI,passwordLI).addOnCompleteListener{
                signIn ->
                if(signIn.isSuccessful){
                    Toast.makeText(this,"Signed in successfully!",Toast.LENGTH_LONG).show()
                    startActivity( Intent(this, HomeActivity::class.java))
                }
                else{
                    Toast.makeText(this,"Signed in Failed!",Toast.LENGTH_LONG).show()

                }
            }
        }
    }
}