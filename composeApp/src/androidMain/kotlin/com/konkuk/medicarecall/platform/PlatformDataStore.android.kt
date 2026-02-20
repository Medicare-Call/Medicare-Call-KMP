package com.konkuk.medicarecall.platform

private var appFilesDir: String = ""

fun initDataStorePath(filesDir: String) {
    appFilesDir = filesDir
}

actual fun dataStorePath(fileName: String): String {
    return "$appFilesDir/$fileName"
}
