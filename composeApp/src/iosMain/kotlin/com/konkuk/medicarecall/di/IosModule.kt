package com.konkuk.medicarecall.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioSerializer
import androidx.datastore.core.okio.OkioStorage
import com.konkuk.medicarecall.data.model.ElderIds
import com.konkuk.medicarecall.data.model.Token
import com.konkuk.medicarecall.data.repository.FcmRepository
import com.konkuk.medicarecall.data.repositoryimpl.IosFcmRepositoryImpl
import com.konkuk.medicarecall.platform.dataStorePath
import kotlinx.serialization.json.Json
import okio.BufferedSink
import okio.BufferedSource
import okio.FileSystem
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private object TokenOkioSerializer : OkioSerializer<Token> {
    override val defaultValue: Token = Token(null, null)

    override suspend fun readFrom(source: BufferedSource): Token {
        return try {
            val json = source.readUtf8()
            if (json.isEmpty()) defaultValue
            else Json.decodeFromString(json)
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: Token, sink: BufferedSink) {
        sink.writeUtf8(Json.encodeToString(Token.serializer(), t))
    }
}

private object ElderIdsOkioSerializer : OkioSerializer<ElderIds> {
    override val defaultValue: ElderIds = ElderIds(emptyMap())

    override suspend fun readFrom(source: BufferedSource): ElderIds {
        return try {
            val json = source.readUtf8()
            if (json.isEmpty()) defaultValue
            else Json.decodeFromString(json)
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: ElderIds, sink: BufferedSink) {
        sink.writeUtf8(Json.encodeToString(ElderIds.serializer(), t))
    }
}

val iosModule: Module = module {
    single<DataStore<Token>>(named("TokenDataStore")) {
        DataStoreFactory.create(
            storage = OkioStorage(
                fileSystem = FileSystem.SYSTEM,
                serializer = TokenOkioSerializer,
                producePath = { dataStorePath("tokens").toPath() },
            ),
        )
    }

    single<DataStore<ElderIds>>(named("ElderIdDataStore")) {
        DataStoreFactory.create(
            storage = OkioStorage(
                fileSystem = FileSystem.SYSTEM,
                serializer = ElderIdsOkioSerializer,
                producePath = { dataStorePath("elderIds").toPath() },
            ),
        )
    }

    single { IosFcmRepositoryImpl() } bind FcmRepository::class
}
