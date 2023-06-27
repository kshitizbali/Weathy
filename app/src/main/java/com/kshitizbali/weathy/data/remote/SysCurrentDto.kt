package com.kshitizbali.weathy.data.remote

import com.squareup.moshi.Json
data class SysCurrentDto(
    @field:Json(name = "sunrise")
    val sunrise: Long,
    @field:Json(name = "sunset")
    val sunset: Long,
)