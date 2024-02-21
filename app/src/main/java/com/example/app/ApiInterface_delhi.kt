package com.example.app

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface_delhi {
    @GET("delhi?start=72&end=90")
    fun getdelhiData(): Call<MyData_delhi>
}