package example.com.core.di

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import example.com.core.security.data.service.JWTTokenService
import example.com.core.security.data.service.SHA1PRNGSaltGenerator
import example.com.core.security.data.service.SHA256HashService
import example.com.core.security.domain.service.HashingService
import example.com.core.security.domain.service.SaltGenerator
import example.com.core.security.domain.service.TokenService
import example.com.core.util.DefaultDisptacherProvider
import example.com.core.util.DispatcherProvider
import example.com.core.util.InputValidators
import org.koin.dsl.module

private const val connectionString =
    "replace"
private const val dbName = "replace"
private val serverApi = ServerApi.builder()
    .version(ServerApiVersion.V1)
    .build()
private val mongoClientSettings = MongoClientSettings.builder()
    .applyConnectionString(ConnectionString(connectionString))
    .serverApi(serverApi)
    .build()

val appModule = module {
    single<MongoClient> {
        MongoClient.create(mongoClientSettings)
    }
    single<MongoDatabase> {
        get<MongoClient>().getDatabase(dbName)
    }
    single<DispatcherProvider> {
        DefaultDisptacherProvider()
    }

    single<InputValidators> {
        InputValidators()
    }

    single<SaltGenerator> {
        SHA1PRNGSaltGenerator()
    }
    single<TokenService> {
        JWTTokenService()
    }
    single<HashingService> {
        SHA256HashService(
            stringHasher = get(),
            saltGenerator = get()
        )
    }
}