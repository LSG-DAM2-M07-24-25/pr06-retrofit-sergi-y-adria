package com.sergiadria.marvelapi.model

data class StoryList(
    val available: Int,
    val items: List<StorySummary>
)
