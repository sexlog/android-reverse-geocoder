package br.com.esapiens.geocoder_library

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by esapiens on 21/08/17.
 */
internal class HttpClientBuilder {
    companion object Methods {
        @JvmStatic
        fun build() : OkHttpClient.Builder {
            val client = OkHttpClient.Builder()
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .writeTimeout(120000, TimeUnit.MILLISECONDS)
                    .readTimeout(60000, TimeUnit.MILLISECONDS)

            return client
        }
    }
}