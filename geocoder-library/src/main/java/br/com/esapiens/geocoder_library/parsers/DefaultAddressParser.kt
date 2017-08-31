package br.com.esapiens.geocoder_library.parsers

import android.location.Address
import br.com.esapiens.geocoder_library.AddressParser
import br.com.esapiens.geocoder_library.entities.GoogleAddressResult

/**
 * Created by esapiens on 21/08/17.
 */
class DefaultAddressParser : AddressParser {
    override fun parseFromCoreApi(address: Address): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("${address.locality}, ${address.adminArea}, ${address.countryName}")
        if (address.maxAddressLineIndex > 0) {
            stringBuilder.append(", ${address.getAddressLine(0)}")
        }
        if (address.featureName != null) {
            stringBuilder.append(", ${address.featureName}")
        }
        return stringBuilder.toString()
    }

    override fun parseFromGoogleApi(results: List<GoogleAddressResult>): String {
        return results.first().formattedAdress
    }
}