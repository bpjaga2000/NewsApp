package `in`.bpj4.newsapp.presentation

import androidx.annotation.DrawableRes

data class Page (
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)


