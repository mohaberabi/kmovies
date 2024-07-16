package example.com.features.auth.di

import example.com.features.auth.data.repository.MongoAuthRepository
import example.com.features.auth.domain.repository.AuthRepository
import org.koin.dsl.module


val authModule = module {


    single<AuthRepository> {
        MongoAuthRepository(
            get(), get(), get(), get(),
        )
    }
}