package com.prongbang.androidsslpinning.testx.domain


import com.google.gson.annotations.SerializedName

data class TestResponse(
		@SerializedName("args")
		val args: Args = Args(),
		@SerializedName("headers")
		val headers: Headers = Headers(),
		@SerializedName("origin")
		val origin: String = "",
		@SerializedName("url")
		val url: String = "",
		@SerializedName("data")
		val data: String = "",
		@SerializedName("files")
		val files: Map<String, Any> = mapOf(),
		@SerializedName("form")
		val form: Map<String, Any> = mapOf(),
		@SerializedName("json")
		val json: Content = Content()
)