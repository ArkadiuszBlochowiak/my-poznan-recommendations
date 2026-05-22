package com.example.mypoznan.ui

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mypoznan.R
import com.example.mypoznan.data.LocalRecommendationDataProvider
import com.example.mypoznan.model.Category
import com.example.mypoznan.model.Recommendation
import com.example.mypoznan.ui.utils.MyPoznanNavigationType
import com.example.mypoznan.ui.utils.NavigationItem
import com.example.mypoznan.ui.utils.NavigationItemList


@Composable
fun RecommendationListScreen(
    uiState: MyPoznanUiState,
    onTabPressed: (Category) -> Unit,
    navigationType: MyPoznanNavigationType,
    onItemClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier,
) {
    val navigationItems: List<NavigationItem> = NavigationItemList.getNavigationList()

    if (navigationType == MyPoznanNavigationType.NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(
                    Modifier.width(
                        dimensionResource(R.dimen.drawer_width)
                    )
                ) {
                    NavigationDrawerContent(
                        navigationItems = navigationItems,
                        currentTab = uiState.currentCategory,
                        onTabPressed = onTabPressed
                    )
                }
            },
            modifier = Modifier.testTag(
                stringResource(R.string.navigation_drawer)
            )
        ) {
            RecommendationListContent(
                uiState = uiState,
                onTabPressed = onTabPressed,
                navigationType = navigationType,
                navigationItems = navigationItems,
                onItemClick = onItemClick,
                modifier = modifier
            )
        }
    } else {
        RecommendationListContent(
            uiState = uiState,
            onTabPressed = onTabPressed,
            navigationType = navigationType,
            navigationItems = navigationItems,
            onItemClick = onItemClick,
            modifier = modifier
        )
    }
}

@Composable
fun RecommendationListContent(
    uiState: MyPoznanUiState,
    onTabPressed: (Category) -> Unit,
    navigationType: MyPoznanNavigationType,
    navigationItems: List<NavigationItem>,
    onItemClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier,
) {
    val resources = LocalResources.current
    val list = if(uiState.currentCategory == Category.ALL) {
        LocalRecommendationDataProvider.getAllRecommendations(resources)
    } else {
        LocalRecommendationDataProvider.getCategoryRecommendations(uiState.currentCategory, resources)
    }

    Row(
        modifier = modifier
    ) {
        AnimatedVisibility(visible = navigationType == MyPoznanNavigationType.NAVIGATION_RAIL) {
            NavigationSideRail(
                navigationItems = navigationItems,
                currentTab = uiState.currentCategory,
                onTabPressed = onTabPressed,
                modifier = Modifier.testTag(
                    stringResource(R.string.navigation_rail)
                )
            )
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            RecommendationList(
                recommendations = list,
                onItemClick = onItemClick,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            )
            AnimatedVisibility(visible = navigationType == MyPoznanNavigationType.BOTTOM_NAVIGATION) {
                NavigationBottomBar(
                    navigationItems = navigationItems,
                    currentTab = uiState.currentCategory,
                    onTabPressed = onTabPressed,
                    modifier = Modifier.testTag(
                        stringResource(R.string.bottom_navigation)
                    )
                )
            }
        }
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
                    NavigationItemIcon(item.icon, item.text)
                }
            )
        }
    }
}

@Composable
fun NavigationSideRail(
    navigationItems: List<NavigationItem>,
    currentTab: Category,
    onTabPressed: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for (item in navigationItems) {
            NavigationRailItem(
                selected = currentTab == item.category,
                onClick = { onTabPressed(item.category) },
                icon = {
                    NavigationItemIcon(item.icon, item.text)
                }
            )
        }
    }
}

@Composable
fun NavigationDrawerContent(
    navigationItems: List<NavigationItem>,
    currentTab: Category,
    onTabPressed: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        for (item in navigationItems) {
            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(item.text)
                    )
                },
                selected = currentTab == item.category,
                onClick = { onTabPressed(item.category) },
                icon = {
                    NavigationItemIcon(item.icon, item.text)
                }
            )
        }
    }
}

@Composable
fun NavigationItemIcon(
    imageVector: ImageVector,
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = imageVector,
        contentDescription = stringResource(description),
        modifier = modifier
    )
}