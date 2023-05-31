package network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface YelpApiService {
    @GET("business/search")
    suspend fun searchRestaurants(@Query("term") searchTerm: String, @Query("location") location: String) : Call<Any>
}
