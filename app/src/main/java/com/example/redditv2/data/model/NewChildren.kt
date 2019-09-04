package com.example.redditv2.data.model

import com.example.redditv2.domain.Children

data class ChildrenResponse(val children: List<ChildrenDataResponse>?){
    fun toDataResponse(): Children = Children(children = children!!.toChildrenData())
}

