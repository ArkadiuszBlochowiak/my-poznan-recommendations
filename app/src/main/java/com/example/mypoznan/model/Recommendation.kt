package com.example.mypoznan.model

import androidx.annotation.IntRange
import androidx.annotation.StringRes

const val MIN_RATING = 1
const val MAX_RATING = 5

data class Recommendation(
    val id: Long,
    @param:StringRes val titleRes: Int,
    @param:StringRes val descriptionRes: Int,
    val category: Category,
    @param:IntRange(from = MIN_RATING.toLong(), to = MAX_RATING.toLong()) val rating: Int,
)
