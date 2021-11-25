package com.prerna.demo.ui.newsappui

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.prerna.news.data.response.NewsArticle
import com.prerna.demo.ui.commoncomposable.HeightSpacer
import com.prerna.demo.ui.commoncomposable.RemoteImage
import com.prerna.demo.ui.commoncomposable.WidthSpacer
import com.prerna.demo.ui.style.NewsTheme
import com.prerna.demo.ui.style.articleTitleStyle
import com.prerna.demo.ui.style.dateTextStyle
import com.prerna.demo.ui.style.sourceTextStyle
import com.prerna.demo.util.CustomTabUtil

@Composable
fun ArticleRow(article: NewsArticle, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = { onClick() })) {
        Row(
                modifier = Modifier.padding(all = 10.dp),
                verticalGravity = Alignment.CenterVertically
        ) {
            RemoteImage(
                    url = article.urlToImage,
                    modifier = Modifier.preferredSize(100.dp)
            )
            WidthSpacer(value = 10.dp)
            Column {
                if (!article.source.name.isNullOrEmpty()) {
                    Text(
                            text = article.source.name!!,
                            style = sourceTextStyle.copy(color = NewsTheme.colors.sourceColor)
                    )
                    HeightSpacer(value = 4.dp)
                }
                Text(
                        text = article.title,
                        style = articleTitleStyle.copy(color = NewsTheme.colors.titleColor),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                )
                HeightSpacer(value = 4.dp)
                Text(
                        text = article.publishedAt.substring(0, 10),
                        style = dateTextStyle.copy(color = NewsTheme.colors.sourceColor)
                )
            }
        }
        HeightSpacer(value = 10.dp)
        Divider(
                color = NewsTheme.colors.dividerColor
        )
    }
}

@Composable
fun ArticleList(articles: List<NewsArticle>) {
    val context = ContextAmbient.current
    val isDark = NewsTheme.colors.isDark
    LazyColumnFor(
            items = articles,
            itemContent = { article: NewsArticle ->
                ArticleRow(
                        article = article,
                        onClick = {
                            CustomTabUtil.launch(context, article.url.toString(), isDark)
                        }
                )
            }
    )
}
