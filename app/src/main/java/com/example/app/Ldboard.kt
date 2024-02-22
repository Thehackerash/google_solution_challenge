package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Ldboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ldboard)

        val listViewLko = findViewById<ListView>(R.id.listViewLko)
        val listViewDelhi = findViewById<ListView>(R.id.listViewDelhi)
        val listViewKanpur = findViewById<ListView>(R.id.listViewbangalore)

        val tasklistLko = arrayListOf<String>()
        val tasklistDelhi = arrayListOf<String>()
        val tasklistKanpur = arrayListOf<String>()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://ly3f.onrender.com/predict/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiInterfaceLko = retrofit.create(ApiInterface_lko::class.java)
        val apiInterfaceDelhi = retrofit.create(ApiInterface_delhi::class.java)
        val apiInterfaceKanpur = retrofit.create(ApiInterface_bangalore::class.java)

            val retrofitdatalko = apiInterfaceLko.getLkoData()
        retrofitdatalko.enqueue(object : Callback<MyData_lko> {
            override fun onResponse(call: Call<MyData_lko>, response: Response<MyData_lko>) {
                val responseBody = response.body()
                val lkodata = responseBody?.`2021` ?: ""
                tasklistLko.add(lkodata.toString())
                listViewLko.adapter = ArrayAdapter(this@Ldboard, android.R.layout.simple_list_item_1, tasklistLko)
            }

            override fun onFailure(call: Call<MyData_lko>, t: Throwable) {
                tasklistLko.add("Error fetching data for Lko")
                listViewLko.adapter = ArrayAdapter(this@Ldboard, android.R.layout.simple_list_item_1, tasklistLko)
            }
        })

        val retrofitdataDelhi = apiInterfaceDelhi.getdelhiData()
        retrofitdataDelhi.enqueue(object : Callback<MyData_delhi> {
            override fun onResponse(call: Call<MyData_delhi>, response: Response<MyData_delhi>) {
                val responseBody = response.body()
                val delhiData = responseBody?.`2021` ?: ""
                tasklistDelhi.add(delhiData.toString())
                listViewDelhi.adapter = ArrayAdapter(this@Ldboard, android.R.layout.simple_list_item_1, tasklistDelhi)
            }

            override fun onFailure(call: Call<MyData_delhi>, t: Throwable) {
                tasklistDelhi.add("Error fetching data for Delhi")
                listViewDelhi.adapter = ArrayAdapter(this@Ldboard, android.R.layout.simple_list_item_1, tasklistDelhi)
            }
        })

        val retrofitdataKanpur = apiInterfaceKanpur.getbangaloreData()
        retrofitdataKanpur.enqueue(object : Callback<MyData_bangalore> {
            override fun onResponse(call: Call<MyData_bangalore>, response: Response<MyData_bangalore>) {
                val responseBody = response.body()
                val bangaloreData = responseBody?.`2021` ?: ""
                tasklistKanpur.add(bangaloreData.toString())
                listViewKanpur.adapter = ArrayAdapter(this@Ldboard, android.R.layout.simple_list_item_1, tasklistKanpur)
            }

            override fun onFailure(call: Call<MyData_bangalore>, t: Throwable) {
                tasklistKanpur.add("Error fetching data for Bangalore")
                listViewKanpur.adapter = ArrayAdapter(this@Ldboard, android.R.layout.simple_list_item_1, tasklistKanpur)
            }
        })
    }
}
