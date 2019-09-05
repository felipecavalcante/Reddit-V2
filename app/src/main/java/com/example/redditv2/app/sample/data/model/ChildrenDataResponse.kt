package com.example.redditv2.app.sample.data.model

import com.example.redditv2.app.sample.domain.ChildrenData


data class ChildrenDataResponse(val data: NewsResponse?) {
    fun toChildrenData(): ChildrenData = ChildrenData(data = data!!.toNews())
}
fun List<ChildrenDataResponse>.toChildrenData() = map {it.toChildrenData()}
