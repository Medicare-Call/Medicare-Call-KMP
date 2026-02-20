package com.konkuk.medicarecall.platform

import android.content.Intent
import android.net.Uri
import com.konkuk.medicarecall.BuildConfig

actual fun getBaseUrl(): String = BuildConfig.BASE_URL

private var appContext: android.content.Context? = null

fun initPlatform(context: android.content.Context) {
    appContext = context.applicationContext
}

actual fun makePhoneCall(number: String) {
    val context = appContext ?: return
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$number")
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    context.startActivity(intent)
}
