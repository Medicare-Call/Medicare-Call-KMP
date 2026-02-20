package com.konkuk.medicarecall.platform

import android.util.Log

actual fun logDebug(tag: String, message: String) {
    Log.d(tag, message)
}
