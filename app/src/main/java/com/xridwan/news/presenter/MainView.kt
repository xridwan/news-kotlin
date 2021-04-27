package com.xridwan.news.presenter

import com.xridwan.news.model.SourceList

interface MainView {
    fun onSuccess(message: String?, sourceList: MutableList<SourceList>?)
    fun onFailure(message: String?)
    fun onLoading(state: Boolean)
}