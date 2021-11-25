package com.prerna.demo.ui.newsappui

import androidx.compose.foundation.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.prerna.demo.other.Category
import com.prerna.demo.ui.style.NewsTheme

@Composable
fun BottomBar(
        categoryList: List<Category>,
        activeCategory: Category,
        onMenuClicked: (Category) -> Unit
) {
    BottomAppBar(
            backgroundColor = NewsTheme.colors.bottomNavBackground,
            elevation = 10.dp
    ) {
        Row(
                modifier = Modifier.fillMaxWidth().preferredHeight(50.dp),
                horizontalArrangement = Arrangement.SpaceAround
        ) {
            categoryList.forEach { category ->
                BottomNavigationItem(
                        icon = { Icon(asset = vectorResource(id = category.icon)) },
                        selected = activeCategory == category,
                        onSelect = {
                            onMenuClicked(category)
                        },
                        selectedContentColor = NewsTheme.colors.bottomNavActiveIconColor,
                        unselectedContentColor = NewsTheme.colors.bottomNavInActiveIconColor
                )
            }
        }
    }
}
