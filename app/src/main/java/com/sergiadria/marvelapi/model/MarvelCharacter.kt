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
