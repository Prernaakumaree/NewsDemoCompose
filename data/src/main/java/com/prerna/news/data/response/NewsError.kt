package com.prerna.news.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsError(
    val code: String,

    val message: String,

    val status: String
)
