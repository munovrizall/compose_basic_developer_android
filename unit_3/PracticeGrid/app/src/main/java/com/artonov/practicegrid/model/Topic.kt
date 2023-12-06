package com.artonov.practicegrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val titleResourceId: Int,
    val label: Int,
    @DrawableRes val drawableResourceId: Int,
)
