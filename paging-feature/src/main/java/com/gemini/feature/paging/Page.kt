package com.gemini.feature.paging

data class Page<T: Any>(
    val items:List<T>,
    val page: Int,
    val hasNextPage: Boolean
)