package com.prongbang.androidsslpinning.network

import com.datatheorem.android.trustkit.pinning.OkHttp3Helper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	private const val BASE_URL = "https://httpbin.org/"

	@Singleton
	@Provides
	fun provide(okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.client(okHttpClient)
				.build()
	}

	@Singleton
	@Provides
	fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
			.sslSocketFactory(OkHttp3Helper.getSSLSocketFactory(), OkHttp3Helper.getTrustManager())
			.addInterceptor(OkHttp3Helper.getPinningInterceptor())
			.addInterceptor(HttpLoggingInterceptor().apply {
				setLevel(HttpLoggingInterceptor.Level.BODY)
			})
			.followRedirects(false)
			.followSslRedirects(false)
			.build()
}