package com.example.parliamentsearch

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Define the base URL of the API
private const val BASE_URL =
    "https://users.metropolia.fi/~peterh/"

// Create a Moshi object with KotlinJsonAdapterFactory
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Create a Retrofit object with MoshiConverterFactory and the base URL
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Define an interface for the Parliament API service
interface ParliamentApiService {
    @GET("seating.json")
    suspend fun getMemberList() : List<MemberEntry>
}
// Create a singleton object for the Parliament API
object MemberApi {
    // Use the lazy function to create the ParliamentApiService instance only when it's needed
    val retrofitService : ParliamentApiService by lazy {
        retrofit.create(ParliamentApiService::class.java)
    }
}
