package br.com.esapiens.geocoder_library

import android.location.Address

/**
 * Created by esapiens on 21/08/17.
 */
class DefaultAddressParser : AddressParser {
    override fun parse(address: Address): String {
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
}