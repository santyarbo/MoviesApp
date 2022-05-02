package es.santyarbo.mymovies.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import es.santyarbo.mymovies.BuildConfig
import es.santyarbo.mymovies.data.network.movies.MoviesService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.Locale

@ExperimentalSerializationApi
class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val HEADER_ACCEPT_LANGUAGE = "Accept-Language"
    }

    val moviesService: MoviesService by lazy {
        buildRetrofit().create(MoviesService::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buildClient())
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .addInterceptor(getHeaderInterceptor())
            .build()
    }

    private fun getHeaderInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()
            val builder = request.newBuilder()
            builder.header(
                HEADER_ACCEPT_LANGUAGE,
                Locale.getDefault().language
            )
            it.proceed(builder.build())
        }
    }

    private fun getHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().also {
            if (BuildConfig.DEBUG) {
                it.level = HttpLoggingInterceptor.Level.BODY
            } else {
                it.level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}