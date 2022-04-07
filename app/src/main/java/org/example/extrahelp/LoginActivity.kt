package org.example.extrahelp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        val email = findViewById<EditText>(R.id.et_login_email)
        val password = findViewById<EditText>(R.id.et_login_password)
        val submitButton = findViewById<Button>(R.id.btn_login)
        submitButton.setOnClickListener {
           when {
               TextUtils.isEmpty(email.text.toString().trim {it <= ' '}) -> {
                   Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
               }

               TextUtils.isEmpty(password.text.toString().trim {it <= ' '}) -> {
                   Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
               }
           else -> {
                val emailText: String = email.text.toString().trim { it <= ' '}
                val passwordText: String = password.text.toString().trim { it <= ' '}

               signIn(emailText, passwordText)
           }
           }
        }
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "Welcome to ExtraHelp!!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, StudentPortal::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "You're unregistered, contact admin!!", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {
        val mainActivityIntent = Intent(this, StudentPortal::class.java)
        startActivity(mainActivityIntent)
    }

}