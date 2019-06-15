package com.example.rxjava2newsapi.news_api

import com.example.rxjava2newsapi.model.TopHeadlines
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadlinesEndpoint {

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Observable<TopHeadlines>

    @GET("everything")
    fun getUserSearchInput(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("q") q: String
    ): Observable<TopHeadlines>
}