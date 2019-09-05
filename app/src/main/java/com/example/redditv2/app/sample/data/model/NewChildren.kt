package com.example.redditv2.app.sample.data.model

import com.example.redditv2.app.sample.domain.Children

data class ChildrenResponse(val children: List<ChildrenDataResponse>?){
    fun toDataResponse(): Children = Children(children = children!!.toChildrenData())
}

