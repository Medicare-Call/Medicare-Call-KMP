package com.konkuk.medicarecall.platform

expect object TokenEncryptorProvider {
    fun encrypt(bytes: ByteArray): ByteArray
    fun decrypt(bytes: ByteArray): ByteArray
}

expect object FcmTokenEncryptorProvider {
    fun encrypt(bytes: ByteArray): ByteArray
    fun decrypt(bytes: ByteArray): ByteArray
}
