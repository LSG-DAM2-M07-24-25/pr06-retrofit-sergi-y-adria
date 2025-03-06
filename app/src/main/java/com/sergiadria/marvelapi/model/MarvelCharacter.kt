package com.sergiadria.marvelapi.model

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: ComicList,
    val series: SeriesList,
    val stories: StoryList
) {
    val imageUrl: String
        get() = "${thumbnail.path.replace("http", "https")}.${thumbnail.extension}"
}

data class Thumbnail(
    val path: String,
    val extension: String
)

data class ComicList(
    val available: Int,
    val items: List<ComicSummary>
)

data class ComicSummary(
    val name: String
)

data class SeriesList(
    val available: Int,
    val items: List<SeriesSummary>
)

data class SeriesSummary(
    val name: String
)

data class StoryList(
    val available: Int,
    val items: List<StorySummary>
)

data class StorySummary(
    val name: String
)
