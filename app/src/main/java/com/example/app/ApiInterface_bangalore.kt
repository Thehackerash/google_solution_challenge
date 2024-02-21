package com.example.app

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface_bangalore {
    @GET("bangalore?start=72&end=90")
    fun getbangaloreData(): Call<MyData_bangalore>
}