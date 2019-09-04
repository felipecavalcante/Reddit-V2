package com.example.redditv2.domain

data class News(
    var id: String,
    var name: String,
    var title: String,
    var selftext: String,
    var url: String,
    var thumbnail: String,
    var author: String
)