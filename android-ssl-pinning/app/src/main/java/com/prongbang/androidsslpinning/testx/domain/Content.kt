package com.prongbang.androidsslpinning.testx.domain

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)