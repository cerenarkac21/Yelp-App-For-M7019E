package com.ltu.m7019e.yelpapp.network

import com.ltu.m7019e.yelpapp.model.YelpSearchResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.ltu.m7019e.yelpapp.utils.Constants.BASE_URL
import retrofit2.Call
import retrofit2.http.Header
import java.util.concurrent.TimeUnit

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 * (JSON to Kotlin mapper)
 */
private val moshi = Moshi.Builder()
    // use the built-in adapter factory to map the JSON into your classes
    .add(KotlinJsonAdapterFactory())
    .build()


/**
 * Add a httpclient logger for debugging purpose
 * object.
 * Also it enables you to define your own encryption/decryption logic
 */
fun getLoggerInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}



/**
 * Refrofit is a HTTP client that will query the API endpoints
 * Moshi will parse JSON objects to Kotlin objects
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */

private val retrofit = Retrofit.Builder()
    // create a client
    .client(
        // to use the intercepter defined above
        OkHttpClient.Builder()
            .addInterceptor(getLoggerInterceptor())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    )
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


val yelpApiService: YelpApiService by lazy {
    retrofit.create(YelpApiService::class.java)
}
public interface YelpApiService {
    @GET("businesses/search")
    fun searchRestaurants(
        @Header("Authorization") authHeader: String,
        @Query("term") searchTerm: String,
        @Query("location") location: String) : Call<YelpSearchResult>
}
