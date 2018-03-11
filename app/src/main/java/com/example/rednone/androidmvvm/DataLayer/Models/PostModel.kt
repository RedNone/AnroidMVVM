package com.example.rednone.androidmvvm.DataLayer.Models

import com.google.gson.annotations.SerializedName

/**
 * Created by RedNone on 04.03.2018.
 */
data class PostModel(@SerializedName("userId") val userId: Int,
                     @SerializedName("id") val id: Int,
                     @SerializedName("title") val title: String,
                     @SerializedName("body") val body: String)
