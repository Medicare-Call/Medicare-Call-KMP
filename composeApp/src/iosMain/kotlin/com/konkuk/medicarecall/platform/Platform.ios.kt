package com.konkuk.medicarecall.platform

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun getBaseUrl(): String = "" // TODO: Set from Info.plist or hardcode

actual fun makePhoneCall(number: String) {
    val url = NSURL.URLWithString("tel://$number") ?: return
    UIApplication.sharedApplication.openURL(url)
}
