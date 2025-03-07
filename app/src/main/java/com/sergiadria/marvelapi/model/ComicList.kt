package com.sergiadria.marvelapi.model

data class ComicList(
    val available: Int,
    val items: List<ComicSummary>
)