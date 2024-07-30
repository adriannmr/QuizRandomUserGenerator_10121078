package com.adrian.quizrandomusergenerator_10121078

import retrofit2.Call
import retrofit2.http.GET
import com.adrian.quizrandomusergenerator_10121078.models.User

interface ApiService {
    @GET("api/?results=10") // Mendapatkan 10 pengguna secara default
    fun getUsers(): Call<Response>

    data class Response(
        val results: List<User>
    )
}
