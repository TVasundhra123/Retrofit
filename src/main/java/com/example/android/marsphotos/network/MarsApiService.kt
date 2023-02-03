package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

/**
 * We first specify the base url
 * then we create a retrofit object
 * then we specif an interface that tells retrofit how to communicate with the web service
 * then we basically create an object that provides retrofit service to entire app
 * Moshi is a converter factory that parses the json object received into kotlin objects
 * and it does so by making a model class that is data class that matches
 * key names in json to the value names in data class
 */
private const val BASE_URL =  "https://android-kotlin-fun-mars-server.appspot.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface MarsApiService { // it tells the retrofit how to communicate with the web service
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>
}


object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}
