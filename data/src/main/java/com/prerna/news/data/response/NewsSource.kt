package com.prerna.news.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsSource(
    val name: String?
)
