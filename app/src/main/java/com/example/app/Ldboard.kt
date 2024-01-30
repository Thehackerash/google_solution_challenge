package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class Ldboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ldboard)

        val listview=findViewById<ListView>(R.id.listv)
        val tasklist= arrayListOf<String>()
        tasklist.add("Kanpur")
        tasklist.add("Lucknow")
        tasklist.add("Akbarpur")
        tasklist.add("Faridabad")
        tasklist.add("Noida")
        tasklist.add("Haryana")
        tasklist.add("Prayagraj")

        val adapterformylistview = ArrayAdapter(this,android.R.layout.simple_list_item_1,tasklist)
        listview.adapter=adapterformylistview

    }
}