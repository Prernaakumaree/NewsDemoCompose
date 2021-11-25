package com.prerna.demo.ui.newsappui

import com.prerna.news.data.Result
import com.prerna.news.data.response.NewsArticle

data class ArticleListUiState(
        val isLoading: Boolean = true,
        val list: List<NewsArticle>? = emptyList(),
        val error: Result.Error? = null
)
