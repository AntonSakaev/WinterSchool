package com.example.data.di

import com.example.data.remote.BooksAPI
import com.example.data.remote.BooksRepositoryImpl
import com.example.data.remote.mappers.BooksMapper
import com.example.data.remote.mappers.ItemsMapper
import com.example.domain.remote.BooksRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
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
    fun provideOkHttpClient(): OkHttpClient {
        val apiKey = com.example.data.BuildConfig.API_KEY

        val authInterceptor = Interceptor { chain ->
            val original = chain.request()
            val url = original.url.newBuilder()
                .addQueryParameter("key", apiKey)
                .build()
            chain.proceed(original.newBuilder().url(url).build())
        }

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
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
        itemsMapper: ItemsMapper
    ): BooksRepository {
        return BooksRepositoryImpl(booksAPI, booksMapper, itemsMapper)
    }
}
