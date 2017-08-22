package br.com.esapiens.geocoder_library

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Converter
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Created by esapiens on 21/08/17.
 */
internal class JsonSerializerBuilder {
    companion object Methods {
        private val objectMapper: ObjectMapper = ObjectMapper()

        init {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }

        @JvmStatic
        fun build() : Converter.Factory {
            val jacksonConverterFactory = JacksonConverterFactory.create(objectMapper)
            return jacksonConverterFactory
        }
    }
}