package com.xridwan.news.presenter

import com.xridwan.news.Config
import com.xridwan.news.model.NewsModel
import com.xridwan.news.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPresenter(var newsView: NewsView) {
    fun getNews(id: String) {
        newsView.onLoading(true)
        ApiClient.getRetrofit().getNews(id, Config.API_KEY).enqueue(object : Callback<NewsModel> {
            override fun onResponse(
                call: Call<NewsModel>,
                response: Response<NewsModel>
            ) {
                newsView.onLoading(false)
                if (response.isSuccessful) {
                    val data = response.body()?.articles
                    val message = response.body()?.status

                    newsView.onSuccess(message, data)
                    newsView.onFailure(message)

                } else {
                    newsView.onFailure("")
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                newsView.onLoading(false)
                newsView.onFailure(t.message.toString())
            }
        })
    }
}