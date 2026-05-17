package com.example.mypoznan.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Museum
import androidx.compose.material.icons.outlined.Park
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.mypoznan.R
import com.example.mypoznan.data.LocalRecommendationDataProvider
import com.example.mypoznan.model.Category
import com.example.mypoznan.model.Recommendation
import com.example.mypoznan.ui.theme.MyPoznanTheme

@Composable
fun RecommendationItem(
    item: Recommendation,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
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
    category: Category
) {
    val iconData: Pair<ImageVector, Int> = when(category) {
        Category.PARK -> Pair(Icons.Outlined.Park, R.string.park)
        Category.RESTAURANT -> Pair(Icons.Outlined.Restaurant, R.string.restaurant)
        Category.MONUMENT -> Pair(Icons.Outlined.Museum, R.string.monument)
    }

    Icon(
        imageVector = iconData.first,
        contentDescription = stringResource(iconData.second)
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