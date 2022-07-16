package com.practice.callrecordingapp.networking

import android.graphics.Movie
import com.practice.callrecordingapp.User
import com.practice.callrecordingapp.model.Credential
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("user/login")
    fun loginUser(@Body credential: Credential): Call<User>

    companion object {

        var BASE_URL = "https://call-recording-app.herokuapp.com/api/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}