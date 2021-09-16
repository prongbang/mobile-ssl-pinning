package com.prongbang.androidsslpinning.testx

import com.prongbang.androidsslpinning.testx.data.remote.TestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class TestModule {

	@Provides
	fun provideTestApi(retrofit: Retrofit): TestApi = retrofit.create(TestApi::class.java)
}