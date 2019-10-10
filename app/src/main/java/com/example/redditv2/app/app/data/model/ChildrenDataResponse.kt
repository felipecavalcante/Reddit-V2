package com.example.redditv2.app.app.data.model

import com.example.redditv2.app.app.domain.ChildrenData


data class ChildrenDataResponse(val data: NewsResponse?) {
    fun toChildrenData(): ChildrenData = ChildrenData(data = data!!.toNews())
}
fun List<ChildrenDataResponse>.toChildrenData() =  map {it.toChildrenData()}
