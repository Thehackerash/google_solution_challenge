package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class fdback : AppCompatActivity() {
    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fdback)

        val feed=findViewById<Button>(R.id.button)
        val textfeed=findViewById<EditText>(R.id.etfeed)
        feed.setOnClickListener{
            val feedi = textfeed.text.toString()
            val feedb=Feedb(feedi)
            database = FirebaseDatabase.getInstance().getReference("feedback")
            Toast.makeText(this,"Feedback Submitted",Toast.LENGTH_SHORT).show()
        }
    }
}