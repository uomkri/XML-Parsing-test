package ru.uomkri.marlerinotest.repo.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import ru.uomkri.marlerinotest.BuildConfig

val logging = HttpLoggingInterceptor()
val httpClient = OkHttpClient.Builder()
    .addInterceptor(logging.apply {
    level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(SimpleXmlConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl("https://www.nasa.gov/rss/dyn/")
    .client(httpClient)
    .build()

object RssResource {
    val retrofitService: RssService by lazy {
        retrofit.create(RssService::class.java)
    }
}

interface RssService {
    @GET("breaking_news.rss")
    fun getRssFeed(): Deferred<RssResponseWrapper>
}