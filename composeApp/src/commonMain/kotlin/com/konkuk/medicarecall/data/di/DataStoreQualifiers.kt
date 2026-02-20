package com.konkuk.medicarecall.data.di

import org.koin.core.annotation.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TokenDataStore

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ElderIdDataStore
