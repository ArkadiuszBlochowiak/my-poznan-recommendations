package com.example.mypoznan.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mypoznan.R
import com.example.mypoznan.data.LocalRecommendationDataProvider
import com.example.mypoznan.model.Category
import com.example.mypoznan.ui.utils.NavigationItem
import com.example.mypoznan.ui.utils.NavigationItemList


@Composable
fun RecommendationListScreen(
    uiState: MyPoznanUiState,
    onTabPressed: (Category) -> Unit,
    modifier: Modifier = Modifier,
) {
    val resources = LocalResources.current
    val list = if(uiState.currentCategory == Category.ALL) {
        LocalRecommendationDataProvider.getAllRecommendations(resources)
    } else {
        LocalRecommendationDataProvider.getCategoryRecommendations(uiState.currentCategory, resources)
    }
    val navigationItems: List<NavigationItem> = NavigationItemList.getNavigationList()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        RecommendationList(
            recommendations = list,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
        NavigationBottomBar(
            navigationItems = navigationItems,
            currentTab = uiState.currentCategory,
            onTabPressed = onTabPressed
        )
    }
}

@Composable
fun NavigationBottomBar(
    navigationItems: List<NavigationItem>,
    currentTab: Category,
    onTabPressed: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (item in navigationItems) {
            NavigationBarItem(
                selected = currentTab == item.category,
                onClick = { onTabPressed(item.category) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(item.text)
                    )
                }
            )
        }
    }
}