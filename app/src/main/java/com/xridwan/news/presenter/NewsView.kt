package com.xridwan.news.presenter

import com.xridwan.news.model.Article

interface NewsView {
    fun onSuccess(message: String?, articleList: MutableList<Article>?)
    fun onFailure(message: String?)
    fun onLoading(state: Boolean)
}