package com.konkuk.medicarecall.platform

import com.konkuk.medicarecall.data.util.TokenEncryptor
import com.konkuk.medicarecall.data.util.FcmTokenEncryptor

actual object TokenEncryptorProvider {
    actual fun encrypt(bytes: ByteArray): ByteArray = TokenEncryptor.encrypt(bytes)
    actual fun decrypt(bytes: ByteArray): ByteArray = TokenEncryptor.decrypt(bytes)
}

actual object FcmTokenEncryptorProvider {
    actual fun encrypt(bytes: ByteArray): ByteArray = FcmTokenEncryptor.encrypt(bytes)
    actual fun decrypt(bytes: ByteArray): ByteArray = FcmTokenEncryptor.decrypt(bytes)
}
