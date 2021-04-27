package com.xridwan.news.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MainModel(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("sources")
    val sourceList: MutableList<SourceList>
)

@Parcelize
data class SourceList(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("language")
    val language: String? = null,

    @field:SerializedName("country")
    val country: String? = null
) : Parcelable