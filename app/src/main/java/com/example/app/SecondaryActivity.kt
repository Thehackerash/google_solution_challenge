package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        val home=findViewById<Button>(R.id.home)
        val leaderboard=findViewById<Button>(R.id.Leaderboard)
        val feedback=findViewById<Button>(R.id.Feedback)

        home.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        leaderboard.setOnClickListener{
            val intent = Intent(this,Ldboard::class.java)
            startActivity(intent)
        }

        feedback.setOnClickListener{
            val intent = Intent(this,fdback::class.java)
            startActivity(intent)
        }
    }
}