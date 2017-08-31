package br.com.esapiens.geocoder_library

import android.location.Address
import br.com.esapiens.geocoder_library.entities.GoogleAddressResult

/**
 * Created by esapiens on 21/08/17.
 */
interface AddressParser {
    fun parseFromCoreApi(address: Address): String
    fun parseFromGoogleApi(results: List<GoogleAddressResult>): String
}