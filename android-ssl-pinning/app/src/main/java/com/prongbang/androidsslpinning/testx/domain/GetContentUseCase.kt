package com.prongbang.androidsslpinning.testx.domain

import com.prongbang.androidsslpinning.testx.data.TestRepository
import retrofit2.Response
import javax.inject.Inject

class GetContentUseCase @Inject constructor(
		private val testRepository: TestRepository
) {
	suspend fun execute(): Response<TestResponse> = testRepository.get()
}