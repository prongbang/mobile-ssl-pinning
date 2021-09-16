package com.prongbang.androidsslpinning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import com.google.gson.GsonBuilder
import com.prongbang.androidsslpinning.testx.domain.Content
import com.prongbang.androidsslpinning.testx.domain.GetContentUseCase
import com.prongbang.androidsslpinning.testx.domain.PostContentUseCase
import com.prongbang.androidsslpinning.testx.domain.TestResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

	@Inject
	lateinit var getContentUseCase: GetContentUseCase

	@Inject
	lateinit var postContentUseCase: PostContentUseCase

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		findViewById<AppCompatButton>(R.id.testButton).setOnClickListener {
			testSslPinning()
		}
	}

	private fun testSslPinning() {
		try {
			GlobalScope.launch(Dispatchers.Main) {
				val response = getContentUseCase.execute()
				Timber.v("code: %s", response.code())
			}
		} catch (e: Exception) {
			Timber.e(e)
		}
		try {
			GlobalScope.launch(Dispatchers.Main) {
				val response = postContentUseCase.execute()
				Timber.v("code: %s", response.code())
			}
		} catch (e: Exception) {
			Timber.e(e)
		}
	}
}