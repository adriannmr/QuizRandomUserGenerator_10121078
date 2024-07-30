package com.adrian.quizrandomusergenerator_10121078

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.adrian.quizrandomusergenerator_10121078.models.User

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getUsers().enqueue(object : Callback<ApiService.Response> {
            override fun onResponse(call: Call<ApiService.Response>, response: Response<ApiService.Response>) {
                if (response.isSuccessful) {
                    val users = response.body()?.results ?: emptyList()
                    recyclerView.adapter = UserAdapter(users)
                }
            }

            override fun onFailure(call: Call<ApiService.Response>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
            }
        })
    }
}
