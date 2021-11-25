package com.prerna.demo.ui.newsappui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.prerna.demo.R
import com.prerna.demo.ui.style.NewsTheme
import com.prerna.demo.ui.style.articleTitleStyle

@Composable
fun NewsListContainer(
        uiState: ArticleListUiState,
        retry: () -> Unit
) {
    Surface(
            color = NewsTheme.colors.backGroundColor,
            shape = RoundedCornerShape(topLeft = 5.dp, topRight = 5.dp),
            modifier = Modifier.fillMaxSize().padding(
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 50.dp
            )
    ) {
        when {
            uiState.isLoading -> {
                CircularLoader()
            }
            uiState.error != null -> {
                ErrorView(
                        errorMessage = uiState.error.errorMessage,
                        showRetry = uiState.error.showRetry,
                        retry = retry
                )
            }
            uiState.list?.isEmpty() == false -> {
                ArticleList(
                        articles = uiState.list
                )
            }
        }
    }
}


@Composable
private fun CircularLoader() {
    Column(
            verticalArrangement = Arrangement.Center,
            horizontalGravity = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
                color = NewsTheme.colors.circularLoaderColor
        )
    }
}

@Composable
fun ErrorView(
        errorMessage: String,
        showRetry: Boolean,
        retry: () -> Unit
) {
    Column(
            verticalArrangement = Arrangement.Center,
            horizontalGravity = Alignment.CenterHorizontally
    ) {
        Text(
                text = errorMessage,
                style = articleTitleStyle.copy(color = NewsTheme.colors.titleColor)
        )
        if (showRetry) {
            TextButton(onClick = retry) {
                Text(
                        text = stringResource(id = R.string.retry),
                        style = TextStyle(
                                color = NewsTheme.colors.sourceColor
                        )
                )
            }
        }
    }
}
