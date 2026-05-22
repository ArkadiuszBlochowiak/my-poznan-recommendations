package com.example.mypoznan.ui

import android.text.style.TtsSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Museum
import androidx.compose.material.icons.outlined.Park
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.mypoznan.R
import com.example.mypoznan.data.LocalRecommendationDataProvider
import com.example.mypoznan.model.Category
import com.example.mypoznan.model.MAX_RATING
import com.example.mypoznan.model.MIN_RATING
import com.example.mypoznan.model.Recommendation
import com.example.mypoznan.ui.theme.MyPoznanTheme
import com.example.mypoznan.ui.utils.CategoryConfig

@Composable
fun RecommendationList(
    recommendations: List<Recommendation>,
    modifier: Modifier = Modifier,
    onItemClick: (Recommendation) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = dimensionResource(R.dimen.padding_small)),
        verticalArrangement = Arrangement
            .spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        items(recommendations, key = {item -> item.id}) { item ->
            RecommendationItem(
                item = item,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
fun RecommendationItem(
    item: Recommendation,
    modifier: Modifier = Modifier,
    onItemClick: (Recommendation) -> Unit = {}
) {
    Card(
        modifier = modifier,
        onClick = { onItemClick(item) }
    ) {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Row() {
                    Text(
                        text = stringResource(item.titleRes),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier
                            .padding(bottom = dimensionResource(R.dimen.padding_small))
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    RecommendationItemIcon(
                        category = item.category
                    )
                }
                Text(
                    text = stringResource(item.descriptionRes),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

    }
}

@Composable
fun RecommendationItemIcon(
    category: Category,
    modifier: Modifier = Modifier
) {
    val iconData = CategoryConfig.getCategoryData(category)

    Icon(
        imageVector = iconData.first,
        contentDescription = stringResource(iconData.second),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun RecommendationItemPreview() {
    MyPoznanTheme () {
        RecommendationItem(
            item = LocalRecommendationDataProvider.defaultRecommendation
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationItemDarkThemePreview() {
    MyPoznanTheme (darkTheme = true) {
        RecommendationItem(
            item = LocalRecommendationDataProvider.defaultRecommendation
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationListPreview() {
    MyPoznanTheme {
        val resources = LocalResources.current
        RecommendationList(
            recommendations = LocalRecommendationDataProvider.getAllRecommendations(resources)
        )
    }
}

@Composable
fun RecommendationDetails(
    item: Recommendation,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Text(
            text = stringResource(item.titleRes),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.padding_small))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.category),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                    Text(
                        text = stringResource(R.string.rating),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_large)))
                Column {
                    val categoryText = CategoryConfig.getCategoryData(item.category).second
                    Text(
                        text = stringResource(categoryText)
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                    RecommendationRating(
                        rating = item.rating
                    )
                }
            }
        }
        Text(
            text = stringResource(item.descriptionRes)
        )
    }
}

@Composable
fun RecommendationRating(
    rating: Int,
    modifier: Modifier = Modifier
) {
    val allStars = MAX_RATING
    val outlined = allStars - rating

    Row(modifier = modifier) {
        for (i in MIN_RATING..rating) {
            Icon(
                imageVector = Icons.Filled.StarRate,
                contentDescription = null
            )
        }
        for (i in MIN_RATING..outlined) {
            Icon(
                imageVector = Icons.Outlined.StarRate,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationRatingPreview() {
    MyPoznanTheme () {
        RecommendationRating(
            rating = LocalRecommendationDataProvider.defaultRecommendation.rating
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationDetailsPreview() {
    MyPoznanTheme () {
        RecommendationDetails(
            item = LocalRecommendationDataProvider.defaultRecommendation
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationTopBar(
    isShowingMainPage: Boolean,
    currentRecommendation: Recommendation,
    modifier: Modifier = Modifier,
    onBackButtonPressed: () -> Unit = {}
) {
    val title = if (isShowingMainPage) {
        stringResource(R.string.app_name)
    } else {
        val categoryName = stringResource(
            CategoryConfig.getCategoryData(currentRecommendation.category).second
        )
        stringResource(R.string.category_details, categoryName)
    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (!isShowingMainPage) {
                IconButton(
                    onClick = onBackButtonPressed
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.go_back_button)
                    )
                }
            }
        },
        colors = if (!isShowingMainPage) {
            TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface)
        } else {
            TopAppBarDefaults.topAppBarColors()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RecommendationTopBarMainPagePreview() {
    MyPoznanTheme () {
        RecommendationTopBar(
            true,
            currentRecommendation = LocalRecommendationDataProvider.defaultRecommendation
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationTopBarDetailsPagePreview() {
    MyPoznanTheme (darkTheme = true) {
        RecommendationTopBar(
            false,
            currentRecommendation = LocalRecommendationDataProvider.defaultRecommendation
        )
    }
}

