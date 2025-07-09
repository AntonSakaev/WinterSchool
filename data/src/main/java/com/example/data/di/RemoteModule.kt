package com.example.data.di

import com.example.data.remote.BooksAPI
import com.example.data.remote.BooksRepositoryImpl
import com.example.data.remote.mappers.BooksMapper
import com.example.domain.remote.BooksRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClientInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideRegisterApi(retrofit: Retrofit): BooksAPI {
        return retrofit.create(BooksAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideBooksRepository(
        booksAPI: BooksAPI,
        booksMapper: BooksMapper,
    ): BooksRepository {
        return BooksRepositoryImpl(booksAPI, booksMapper)
    }
}