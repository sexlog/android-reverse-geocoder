package br.com.esapiens.geocoder_library.parsers

import android.location.Address
import br.com.esapiens.geocoder_library.AddressParser
import br.com.esapiens.geocoder_library.entities.AddressComponent
import br.com.esapiens.geocoder_library.entities.AddressType
import br.com.esapiens.geocoder_library.entities.GoogleAddressResult

/**
 * Created by esapiens on 31/08/17.
 */
class CityNameParser(private val useShortName: Boolean): AddressParser {
    private val AdministrativeAreaLevelGeneric = "administrative_area_level"

    override fun parseFromCoreApi(address: Address): String {
        return address.locality
    }

    override fun parseFromGoogleApi(results: List<GoogleAddressResult>): String {
        //First, search if we have an address component containing a Locality (specific city)
        results
                .flatMap { it.addressComponents }
                .forEach {
                    if (it.containsType(AddressType.Locality)) {
                        return getNameFromAddressComponent(it)
                    }
                }

        //Otherwise, look for a result that have the higher administrative_area_level value
        results
                .flatMap { it.addressComponents }
                .filter { it.types.any { it.startsWith(AdministrativeAreaLevelGeneric) } }
                .sortedByDescending { it.types.find { it.contains(AdministrativeAreaLevelGeneric) } }
                .firstOrNull()
                ?.let { return getNameFromAddressComponent(it) }

        throw UnsupportedOperationException()
    }

    private fun getNameFromAddressComponent(addressComponent: AddressComponent): String {
        return when(useShortName) {
            true -> addressComponent.shortName
            else -> addressComponent.longName
        }
    }
}