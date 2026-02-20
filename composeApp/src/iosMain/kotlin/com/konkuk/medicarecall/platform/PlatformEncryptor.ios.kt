package com.konkuk.medicarecall.platform

actual object TokenEncryptorProvider {
    actual fun encrypt(bytes: ByteArray): ByteArray = bytes // TODO: Implement iOS Keychain encryption
    actual fun decrypt(bytes: ByteArray): ByteArray = bytes
}

actual object FcmTokenEncryptorProvider {
    actual fun encrypt(bytes: ByteArray): ByteArray = bytes // TODO: Implement iOS Keychain encryption
    actual fun decrypt(bytes: ByteArray): ByteArray = bytes
}
