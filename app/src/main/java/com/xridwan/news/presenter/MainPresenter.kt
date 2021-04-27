package com.xridwan.news.presenter

import com.xridwan.news.Config
import com.xridwan.news.model.MainModel
import com.xridwan.news.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(var mainView: MainView) {
    fun get() {
        mainView.onLoading(true)
        ApiClient.getRetrofit().get(Config.API_KEY).enqueue(object : Callback<MainModel> {
            override fun onResponse(
                call: Call<MainModel>,
                response: Response<MainModel>
            ) {
                mainView.onLoading(false)
                if (response.isSuccessful) {
                    val data = response.body()?.sourceList
                    val message = response.body()?.status

                    mainView.onSuccess(message, data)
                    mainView.onFailure(message)

                } else {
                    mainView.onFailure("")
                }
            }

            override fun onFailure(call: Call<MainModel>, t: Throwable) {
                mainView.onLoading(false)
                mainView.onFailure(t.message.toString())
            }
        })
    }
}