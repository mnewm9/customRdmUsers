package com.example.genesysanchallenge.network

import com.example.genesysanchallenge.model.UsersModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET("/api/")
    suspend fun getUsers(@Query ("results") quantity: Int): UsersModel
}