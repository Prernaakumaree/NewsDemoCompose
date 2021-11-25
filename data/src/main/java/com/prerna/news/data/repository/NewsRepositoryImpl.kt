package com.prerna.news.data.repository

import com.prerna.news.data.Result
import com.prerna.news.data.apiservice.NewsApiService
import com.prerna.news.data.response.NewsError
import com.prerna.news.data.response.NewsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val moshi: Moshi
) : NewsRepository {
    private val defaultDispatcher = Dispatchers.Default
    override suspend fun getArticlesByCategoryAsync(
        category: String,
        page: Int
    ): Result<NewsResponse> {
        try {
            val response = newsApiService.getArticlesByCateGoryAsync(category)
            return if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error("No Data found")
                }
            } else {
                val jsonAdapter: JsonAdapter<NewsError> = moshi.adapter(
                    NewsError::class.java
                )
                withContext(defaultDispatcher) {
                    val newsError = jsonAdapter.fromJson(response.errorBody()?.string()!!)
                    Result.Error(
                        newsError!!.message,
                        showRetry(newsError.code)
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            var errorMessage = e.localizedMessage
            if (e.localizedMessage!!.contains("Unable to resolve host")) {
                errorMessage = "No internet connection"
            }
            return Result.Error(errorMessage ?: "Something went wrong")
        }
    }

    private fun showRetry(code: String): Boolean = when (code) {
        "apiKeyDisabled", "apiKeyExhausted", "apiKeyInvalid", "apiKeyMissing" -> false
        else -> true
    }
}
