package com.example.rxjava2newsapi.model

data class TopHeadlines(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)