package com.konkuk.medicarecall.platform

actual fun logDebug(tag: String, message: String) {
    println("[$tag] $message")
}
