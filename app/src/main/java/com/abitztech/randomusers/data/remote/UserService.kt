package com.abitztech.randomusers.data.remote

import com.abitztech.randomusers.data.remote.model.response.RandomUsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/")
    fun getUsers(@Query("results") result: Int, @Query("page") page: Int): Call<RandomUsersResponse>
}