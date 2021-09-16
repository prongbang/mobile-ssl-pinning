package com.prongbang.androidsslpinning.testx.data

import com.prongbang.androidsslpinning.testx.data.remote.TestApi
import com.prongbang.androidsslpinning.testx.domain.Content
import com.prongbang.androidsslpinning.testx.domain.TestResponse
import retrofit2.Response
import retrofit2.http.Body
import javax.inject.Inject

class TestRepository @Inject constructor(
		private val testApi: TestApi
) {
	suspend fun get(): Response<TestResponse> = testApi.get()

	suspend fun post(request: Content): Response<TestResponse> = testApi.post(request)
}