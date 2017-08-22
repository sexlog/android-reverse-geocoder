package br.com.esapiens.geocoder_library

import android.location.Location

/**
 * Created by esapiens on 21/08/17.
 */
data class SimpleLocation(val latitude: Double, val longitude: Double)

fun Location.toSimpleLocation() = SimpleLocation(latitude, longitude)