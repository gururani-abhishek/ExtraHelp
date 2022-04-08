package org.example.extrahelp.Studentsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.example.extrahelp.LoginActivity
import org.example.extrahelp.R

class signup_students : AppCompatActivity() {

    lateinit var StudentUsername:TextInputEditText
    lateinit var StudentEmail:TextInputEditText
    lateinit var StudentPassword: EditText
    lateinit var auth: FirebaseAuth
    lateinit var database: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var studentdata:StudentDataSignUp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_students)
        val alread_have_an_account=findViewById<TextView>(R.id.alreadyhaveaccount)

        alread_have_an_account.setOnClickListener(View.OnClickListener {

            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        })

        StudentUsername=findViewById(R.id.usernametext)
        StudentEmail=findViewById(R.id.emailtext)
        StudentPassword=findViewById(R.id.passwordtext2)


        // firebase reference
        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()
        reference=database.getReference()



    }



    fun StudentSubmitted(View:View)
    {
        if(!StudentUsername.text.toString().isEmpty() && !StudentEmail.text.toString().isEmpty() && !StudentPassword.text.toString().isEmpty())
        {

        auth.createUserWithEmailAndPassword(StudentEmail.text.toString(),StudentPassword.text.toString()).addOnSuccessListener {
            task->
            val user: FirebaseUser? = auth.currentUser
            user!!.sendEmailVerification().addOnSuccessListener { verify ->


                Toast.makeText(applicationContext, "Verification Email sent", Toast.LENGTH_SHORT).show()
                val username=StudentUsername.text.toString()
                val studentemail=StudentEmail.text.toString()
                val password=StudentPassword.text.toString()
                studentdata= StudentDataSignUp(username,studentemail,password)

                var purified_email=studentemail.replace(".","")  //is used to remove . from the email as a key cannot have . in firebase
                database.reference.child("Students").child(purified_email).setValue(studentdata).addOnFailureListener {
                    Toast.makeText(applicationContext, "${it.message}", Toast.LENGTH_SHORT).show()
                }.addOnCompleteListener(){
                   Toast.makeText(applicationContext, "Account successfully created!", Toast.LENGTH_SHORT).show()
                    val intent=Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                }


            }
        }.addOnFailureListener {

            Toast.makeText(applicationContext, "${it.message}", Toast.LENGTH_SHORT).show()
        }
        }

    }

}