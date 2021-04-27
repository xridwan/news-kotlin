package com.xridwan.news.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class NewsModel(
    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: MutableList<Article>
)

@Parcelize
data class Article(
    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("description")
    val desc: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("source")
    val sources: Sources,

    ) : Parcelable

@Parcelize
data class Sources(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null,
) : Parcelable



