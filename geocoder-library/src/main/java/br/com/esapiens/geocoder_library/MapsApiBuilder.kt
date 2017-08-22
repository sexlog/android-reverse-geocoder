package br.com.esapiens.geocoder_library

import retrofit2.Retrofit

/**w
 * Created by esapiens on 21/08/17.
 */
internal class MapsApiBuilder {
    companion object builder {
        private val API_URL = "https://maps.googleapis.com/maps/api/"

        @JvmStatic
        fun build() : MapsApi {
            val client = HttpClientBuilder.build().build()
            val converterFactory = JsonSerializerBuilder.build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(client)
                    .addConverterFactory(converterFactory)
                    .build()

            return retrofit.create(MapsApi::class.java)
        }
    }
}