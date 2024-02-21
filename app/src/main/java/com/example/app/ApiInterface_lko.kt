package com.example.app

import retrofit2.http.GET
import retrofit2.Call

interface ApiInterface_lko {
    @GET("lucknow?start=72&end=90")
    fun getLkoData():Call<MyData_lko>
}