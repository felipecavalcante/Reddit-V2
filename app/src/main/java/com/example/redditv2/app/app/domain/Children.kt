package com.example.redditv2.app.app.domain

data class Children(
    val children: List<ChildrenData>,
    val after: String?
)