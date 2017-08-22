package br.com.esapiens.geocoder_library

import android.location.Address

/**
 * Created by esapiens on 21/08/17.
 */
interface AddressParser {
    fun parse(address: Address): String
}