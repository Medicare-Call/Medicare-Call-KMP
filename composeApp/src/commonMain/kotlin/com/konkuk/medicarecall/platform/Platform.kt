package com.konkuk.medicarecall.platform

expect fun getBaseUrl(): String

expect fun makePhoneCall(number: String)
