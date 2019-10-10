package com.example.redditv2.app.app.data.model

import com.example.redditv2.app.app.domain.News

data class NewsResponse(
    var id: String,
    var name: String,
    var title: String,
    var selftext: String,
    var url: String,
    var thumbnail: String,
    var author: String
) {
    fun toNews(): News =
        News(
            id = id,
            name = name,
            title = title,
            selftext = selftext,
            url = url,
            thumbnail = thumbnail,
            author = author
        )
}