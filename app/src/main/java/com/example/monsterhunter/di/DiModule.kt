package com.example.monsterhunter.di

import com.example.monsterhunter.data.Api
import com.example.monsterhunter.data.repositories.ArmorRepository
import com.example.monsterhunter.data.repositories.IArmorRepository
import com.example.monsterhunter.ui.ArmorsViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoriesModule = module {
    singleOf(::ArmorRepository) { bind<IArmorRepository>() }
}

val viewModelsModule = module {
    viewModelOf(::ArmorsViewModel)
}

val networkModule = module {

    singleOf(::OkHttpClient) {
        provideOkHttpClient()
    }
    single<Retrofit> {
        provideRetrofit(get())
    }

    single<Api> {
        provideApi(get())
    }

}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://mhw-db.com/").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
