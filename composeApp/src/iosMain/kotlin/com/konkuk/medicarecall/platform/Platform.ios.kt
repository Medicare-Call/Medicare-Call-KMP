package com.konkuk.medicarecall.platform

import platform.Foundation.NSBundle
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun getBaseUrl(): String {
    return NSBundle.mainBundle.objectForInfoDictionaryKey("BaseURL") as? String ?: ""
}

actual suspend fun getFcmToken(): String = "" // TODO: APNs 토큰 구현

actual fun makePhoneCall(number: String) {
    val url = NSURL.URLWithString("tel://$number") ?: return
    UIApplication.sharedApplication.openURL(url)
}
