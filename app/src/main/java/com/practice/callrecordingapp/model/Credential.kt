package com.practice.callrecordingapp.model


import com.google.gson.annotations.SerializedName

data class Credential(
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)