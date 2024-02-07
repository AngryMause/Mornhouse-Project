package com.example.mornhouseproject.model.api

import com.google.gson.annotations.SerializedName

data class ApiModel(
    @SerializedName("text")
    val text: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("found")
    val found: Boolean,
    @SerializedName("type")
    val trivial: String,
)



