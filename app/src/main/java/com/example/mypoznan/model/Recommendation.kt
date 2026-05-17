package com.example.mypoznan.model

import androidx.annotation.IntRange
import androidx.annotation.StringRes

data class Recommendation(
    val id: Long,
    @param:StringRes val titleRes: Int,
    @param:StringRes val descriptionRes: Int,
    val category: Category,
    @param:IntRange(from = 0, to = 5) val rating: Int,
)
