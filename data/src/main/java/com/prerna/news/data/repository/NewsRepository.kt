package com.prerna.news.data.repository

import com.prerna.news.data.Result
import com.prerna.news.data.response.NewsResponse

interface NewsRepository {
    suspend fun getArticlesByCategoryAsync(category: String, page: Int): Result<NewsResponse>
}
