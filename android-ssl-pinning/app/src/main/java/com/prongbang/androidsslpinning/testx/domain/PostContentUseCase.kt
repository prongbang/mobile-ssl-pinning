package com.prongbang.androidsslpinning.testx.domain

import com.prongbang.androidsslpinning.testx.data.TestRepository
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class PostContentUseCase @Inject constructor(
		private val testRepository: TestRepository
) {
	suspend fun execute(): Response<TestResponse> = testRepository.post(Content(
			id = 1,
			name = UUID.randomUUID()
					.toString()
	))
}