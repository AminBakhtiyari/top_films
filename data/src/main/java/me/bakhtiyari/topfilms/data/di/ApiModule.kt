package me.bakhtiyari.topfilms.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.bakhtiyari.topfilms.data.BuildConfig
import me.bakhtiyari.topfilms.data.interceptor.ApiKeyInterceptor
import me.bakhtiyari.topfilms.data.network.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @HttpLoggingInterceptorOkHttpClient
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.NONE }


    @ApiKeyInterceptorOkHttpClient
    @Singleton
    @Provides
    fun providesApiKeyInterceptor(): ApiKeyInterceptor = ApiKeyInterceptor()


    @Singleton
    @Provides
    fun provideHostnameVerifier(): HostnameVerifier = HostnameVerifier { s, _ -> BuildConfig.BASE_API_URL.contains(s) }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        @HttpLoggingInterceptorOkHttpClient httpLoggingInterceptor: HttpLoggingInterceptor,
        @ApiKeyInterceptorOkHttpClient apiKeyInterceptor: ApiKeyInterceptor,
        hostnameVerifier: HostnameVerifier
    ): OkHttpClient = OkHttpClient()
        .newBuilder()
        .followRedirects(true)
        .followSslRedirects(true)
        .retryOnConnectionFailure(true)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .hostnameVerifier(hostnameVerifier)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(apiKeyInterceptor)
        .build()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    //Qualifiers
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    private annotation class HttpLoggingInterceptorOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    private annotation class ApiKeyInterceptorOkHttpClient
}