package com.prongbang.androidsslpinning.testx.data.remote

import com.prongbang.androidsslpinning.testx.domain.Content
import com.prongbang.androidsslpinning.testx.domain.TestResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TestApi {

	@GET("get")
	suspend fun get(): Response<TestResponse>

	@POST("post")
	suspend fun post(@Body request: Content): Response<TestResponse>
}