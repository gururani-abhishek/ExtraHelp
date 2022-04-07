package org.example.extrahelp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun JumpToStudentPortal(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    fun JumpToFacutlyPortal(view: View) {
        val intent = Intent(this, LoginActivityFacutly::class.java)
        startActivity(intent)
    }
}