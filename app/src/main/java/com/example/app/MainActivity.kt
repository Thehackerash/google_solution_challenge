package com.example.app

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import com.example.app.databinding.ActivityMainBinding
import okhttp3.Authenticator
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//b020f7154be04bba0a652777f0f72c9c
class MainActivity : AppCompatActivity() {

    private val  binding :ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fetchdata("KANPUR")
        SearchCity()
    }

    private fun SearchCity() {
       val searchView=binding.searchView
       searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
           android.widget.SearchView.OnQueryTextListener {


           override fun onQueryTextSubmit(query: String?): Boolean {
               if (query != null) {
                   fetchdata(query)
               }
               return true
           }

           override fun onQueryTextChange(newText: String?): Boolean {
               return true
           }

       })
    }


    private fun fetchdata(cityName:String) {

//        val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("172.31.2.3", 8080))
//        val proxyAuthenticator = Authenticator { route, response ->
//            val credential = Credentials.basic("IIB2022027", "@Asdf123")
//
//            response.request().newBuilder()
//                .header("Proxy-Authorization", credential)
//                .build()
//        }

     val client = OkHttpClient.Builder()
//            .proxy(proxy)
//            .proxyAuthenticator(proxyAuthenticator)
           .build()


        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build()
            .create(api::class.java)
        val response = retrofit.getdata(cityName,"b020f7154be04bba0a652777f0f72c9c","metric")
        response.enqueue(object:Callback<weather>{
            override fun onResponse(call: Call<weather>, response: Response<weather>) {
              val responseBody = response.body()
              if(response.isSuccessful&& responseBody!=null){
                  val temp = responseBody.main.temp.toString()
                  val humidity=responseBody.main.humidity.toString()
                  val speed=responseBody.wind.speed.toString()
                  val sunset=responseBody.sys.sunrise.toString()
                  val sealevel=responseBody.main.pressure.toString()
                  val condition=responseBody.weather.firstOrNull()?.main?: "unknown"
                Log.d("TAG", "Success")
                  binding.temp.text="$temp °C"
                  binding.condition.text=condition
                  binding.humidity.text="$humidity %"
                  binding.sea.text="$sealevel hPa"
                  binding.wind.text="$speed m/s"
                  binding.sunrise.text="$sunset"
                  binding.date.text=date()
                      binding.day.text=day(System.currentTimeMillis())
                      binding.cityname.text="$cityName"
                     // binding.city.text="$cityName"
              }
            }
            override fun onFailure(call: Call<weather>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}", t)
            }

        }
        )

    }

    private fun date(): String {
        val sdf=SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date())

    }

    fun day(timestamp: Long):String
    {
        val sdf=SimpleDateFormat("EEEE", Locale.getDefault())
        return sdf.format(Date())
    }
}