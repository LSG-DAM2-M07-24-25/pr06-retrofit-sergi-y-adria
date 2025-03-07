package com.sergiadria.marvelapi.model

data class SeriesList(
    val available: Int,
    val items: List<SeriesSummary>
)
