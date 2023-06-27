package com.kshitizbali.weathy.data.remote

import com.squareup.moshi.Json
data class WindDto(
    @field:Json(name = "speed")
    val speed: Double,
    @field:Json(name = "degree")
    val degree: Int,
    /*@field:Json(name = "gust")
    val gust: Double*/

)
