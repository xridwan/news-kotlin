package com.xridwan.news.network

import com.xridwan.news.model.MainModel
import com.xridwan.news.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("sources")
    fun get(
        @Query("apiKey") apiKey: String? = null
    ): Call<MainModel>

    @GET("top-headlines")
    fun getNews(
        @Query("sources") sources: String? = null,
        @Query("apiKey") apiKey: String? = null
    ): Call<NewsModel>
}