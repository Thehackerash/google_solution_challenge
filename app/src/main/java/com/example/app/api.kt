package com.example.app



import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface api {
    @GET("weather")
    fun getdata(
        @Query("q") city:String,
        @Query("appid") appid:String,
        @Query("units") units:String
    ): Call<weather>
}
