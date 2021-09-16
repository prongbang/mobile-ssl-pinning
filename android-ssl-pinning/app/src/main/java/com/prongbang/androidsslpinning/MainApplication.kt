package com.prongbang.androidsslpinning

import android.app.Application
import com.datatheorem.android.trustkit.TrustKit
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
		}
		TrustKit.initializeWithNetworkSecurityConfiguration(this)
	}
}