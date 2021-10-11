package com.application.anufriev.data

//import android.telecom.Call
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.application.anufriev.data.Entity.TmdbResultsDto

interface TmdbApi {
    @GET("3/movie/popular")
    fun getFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TmdbResultsDto>
}