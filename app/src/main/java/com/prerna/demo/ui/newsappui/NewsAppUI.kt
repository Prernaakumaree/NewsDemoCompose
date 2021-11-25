package com.prerna.demo.ui.newsappui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.prerna.demo.other.Category
import com.prerna.demo.other.getTitleResource
import com.prerna.demo.ui.style.NewsTheme
import com.prerna.demo.viewmodel.NewsViewModel

@Composable
fun NewsAppUI(viewModel: NewsViewModel) {
    val categoryList = viewModel.categoryList.observeAsState().value!!
    val activeCategory = viewModel.activeCategory.observeAsState().value!!
    val firstCategoryUiState = viewModel.firstCategoryUiState.observeAsState().value!!
    val secondCategoryUiState = viewModel.secondCategoryUiState.observeAsState().value!!
    val thirdCategoryUiState = viewModel.thirdCategoryUiState.observeAsState().value!!
    val listOfUiState = listOf(
            firstCategoryUiState,
            secondCategoryUiState,
            thirdCategoryUiState
    )
    val activeIndex = categoryList.indexOf(activeCategory)
    Scaffold(
            bodyContent = {
                BodyContent(
                        activeCategory = activeCategory,
                        onThemeSwitch = {
                            viewModel.performAction(NewsViewModel.Action.SwitchTheme)
                        },
                        activeCategoryUiStateList = listOfUiState,
                        activeIndex = activeIndex,
                        retryFetchingArticles = { category ->
                            viewModel.performAction(NewsViewModel.Action.FetchArticles(category))
                        }
                )
            },
            bottomBar = {
                BottomBar(
                        categoryList = categoryList,
                        onMenuClicked = { category ->
                            viewModel.performAction(NewsViewModel.Action.ChangePageTo(category))
                        },
                        activeCategory = activeCategory
                )
            }
    )
}

@Composable
fun BodyContent(
        activeCategory: Category,
        activeCategoryUiStateList: List<ArticleListUiState>,
        onThemeSwitch: () -> Unit,
        activeIndex: Int,
        retryFetchingArticles: (Category) -> Unit
) {
    val stringRes = getTitleResource(activeCategory)
    Surface(
            modifier = Modifier.fillMaxSize(),
            color = NewsTheme.colors.primaryColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(stringRes, onThemeSwitch = {
                onThemeSwitch()
            })
           
            when (activeIndex) {
                0 -> {
                    NewsListContainer(
                            uiState = activeCategoryUiStateList[activeIndex],
                            retry = {
                                retryFetchingArticles(activeCategory)
                            }
                    )
                }
                1 -> {
                    NewsListContainer(
                            uiState = activeCategoryUiStateList[activeIndex],
                            retry = {
                                retryFetchingArticles(activeCategory)
                            }
                    )
                }
                2 -> {
                    NewsListContainer(
                            uiState = activeCategoryUiStateList[activeIndex],
                            retry = {
                                retryFetchingArticles(activeCategory)
                            }
                    )
                }
            }

        }
    }
}
