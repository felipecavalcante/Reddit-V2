package com.example.redditv2.app.app.data.model

import com.example.redditv2.app.app.domain.Children

data class ChildrenResponse(
    val children: List<ChildrenDataResponse>?,
    val after: String?
) {
    fun toDataResponse(): Children = Children(
        children = children!!.toChildrenData(),
        after = after
    )
}

