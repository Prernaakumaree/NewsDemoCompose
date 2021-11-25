package com.prerna.demo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import com.prerna.demo.ui.newsappui.NewsAppUI
import com.prerna.demo.ui.style.NewsTheme
import com.prerna.demo.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDemoActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkTheme = viewModel.isDarkTheme.observeAsState(false)
            NewsTheme(darkTheme = darkTheme.value) {
                NewsAppUI(viewModel = viewModel)
            }
        }
    }
}
