# Android SSL Pinning

## Get SHA-256

- https://www.ssllabs.com/ssltest/analyze.html

## Config `network_security_config.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config xmlns:android="http://schemas.android.com/apk/res/android">
	<domain-config>
		<domain includeSubdomains="true">httpbin.org</domain>
		<pin-set>
			<pin digest="SHA-256">J0dKy1gw45muM4o/vm/tskFQ2BWudtp9XLxaW7OtowQ=</pin>
			<pin digest="SHA-256">JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=</pin>
		</pin-set>
		<trustkit-config enforcePinning="true" />
		<trust-anchors>
			<certificates src="system" />
		</trust-anchors>
	</domain-config>
	<base-config cleartextTrafficPermitted="true">
		<trust-anchors>
			<certificates src="system" />
		</trust-anchors>
	</base-config>
</network-security-config>
```

## Initial in `MainApplication.kt'

```kotlin
class MainApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		TrustKit.initializeWithNetworkSecurityConfiguration(this)
	}
}
```

## Config `OkHttpClient`

```kotlin
fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .sslSocketFactory(OkHttp3Helper.getSSLSocketFactory(), OkHttp3Helper.getTrustManager())
        .addInterceptor(OkHttp3Helper.getPinningInterceptor())
        .followRedirects(false)
        .followSslRedirects(false)
        .build()
```

## Use permissions

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Test

### Test with sha256 invalid

- Android

```
Process: com.prongbang.androidsslpinning, PID: 20580
javax.net.ssl.SSLHandshakeException: Pin verification failed
  Configured pins: FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4= XlCPXC6IrttTF9Y1887kS+efCCf3uFjHW6D1TUI9f+Q= 
  Peer certificate chain: 
    J0dKy1gw45muM4o/vm/tskFQ2BWudtp9XLxaW7OtowQ= - CN=httpbin.org
    JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA= - CN=Amazon, OU=Server CA 1B, O=Amazon, C=US
    ++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI= - CN=Amazon Root CA 1, O=Amazon, C=US
    KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I= - CN=Starfield Services Root Certificate Authority - G2, O="Starfield Technologies, Inc.", L=Scottsdale, ST=Arizona, C=US
    at com.android.org.conscrypt.ConscryptFileDescriptorSocket.startHandshake(ConscryptFileDescriptorSocket.java:239)
```

### Test with proxy

- https://medium.com/globant/testing-ssl-pinning-in-a-mobile-application-2dcac9ab3d0c

### Config Burp Proxy

#### Installing Burp's CA Certificate in an Android Device

- https://portswigger.net/support/installing-burp-suites-ca-certificate-in-an-android-device

#### Configuring an Android Device to Work With Burp

- https://portswigger.net/support/configuring-an-android-device-to-work-with-burp


#### Without SSL Pinning

```
javax.net.ssl.SSLHandshakeException: java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.
    at com.android.org.conscrypt.ConscryptFileDescriptorSocket.startHandshake(ConscryptFileDescriptorSocket.java:239)
```
    
#### With SSL Pinning

```
javax.net.ssl.SSLHandshakeException: Certificate validation failed for httpbin.org
    at com.android.org.conscrypt.ConscryptFileDescriptorSocket.startHandshake(ConscryptFileDescriptorSocket.java:239)
```

### Config Charles Proxy

#### Configuration Proxy

- https://hasancanakgunduz.medium.com/testing-ios-ssl-pinning-with-charles-fb68f3aeb473
- https://hackupstate.medium.com/using-charles-proxy-to-debug-android-ssl-traffic-e61fc38760f7

#### Download and install to an device

- http://chls.pro

