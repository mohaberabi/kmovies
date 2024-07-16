package example.com.features.user.di

import com.fishbookserver.features.user.data.repository.MongoUserRepository
import com.fishbookserver.features.user.domain.repository.UserRepository
import org.koin.dsl.module


val userModule = module {


    single<UserRepository> {
        MongoUserRepository(get(), get())
    }
}