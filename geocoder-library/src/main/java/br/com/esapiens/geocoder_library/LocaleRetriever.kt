package br.com.esapiens.geocoder_library

import android.content.Context
import android.location.Geocoder
import br.com.esapiens.geocoder_library.entities.GoogleMapSearch
import br.com.esapiens.geocoder_library.parsers.DefaultAddressParser
import java.util.*

/**
 * Created by esapiens on 21/08/17.
 */
class LocaleRetriever(context: Context, locale: Locale = Locale.getDefault(), private val addressParser: AddressParser = DefaultAddressParser()) {

    constructor(context: Context): this(context, Locale.getDefault(), DefaultAddressParser())

    private val geocoder = Geocoder(context, locale)
    private val mapsApi = MapsApiBuilder.build()

    fun findWithGoogleApi(location: SimpleLocation): String {
        val latitudeAndLongitude = "${location.latitude},${location.longitude}"

        val response = RetrofitSynchronousCallFix.execute(mapsApi, latitudeAndLongitude)
        val body = response?.body()
        if (response!!.isSuccessful && body != null) {
            val formattedAddress = parseGoogleMapsSearch(body)
            if (formattedAddress != null) {
                return formattedAddress
            }

            throw AddressNotFoundException()
        }

        throw GoogleApiFetchException(response.code())
    }

    private fun parseGoogleMapsSearch(googleMapSearch: GoogleMapSearch) : String? {
        if (googleMapSearch.isOK) {
            val results = googleMapSearch.results
            return addressParser.parseFromGoogleApi(results)
        }

        return null
    }

    fun findWithCoreApi(location: SimpleLocation): String {
        if (!Geocoder.isPresent()) {
            throw GeocoderNotPresentException()
        }

        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        if (addresses.size > 0) {
            val parsedAddress = addressParser.parseFromCoreApi(addresses[0])
            return parsedAddress
        }

        throw AddressNotFoundException()
    }

    fun find(firstMethod: LocationRetrievalMethod, location: SimpleLocation): String {
        val methodDictionary = mutableMapOf<LocationRetrievalMethod, (SimpleLocation) -> String>()
        methodDictionary.put(LocationRetrievalMethod.GoogleWebservice, this::findWithGoogleApi)
        methodDictionary.put(LocationRetrievalMethod.InternalGeocoder, this::findWithCoreApi)

        //Creates a queue of reverse-geocoding methods
        val methods = ArrayDeque<LocationRetrievalMethod>()
        methods.add(firstMethod)
        LocationRetrievalMethod.values().filter { it != firstMethod }.forEach { methods.add(it) }

        while(!methods.isEmpty()) {
            val method = methods.pop()
            try {
                val address = methodDictionary[method]!!(location)
                return address
            }
            catch(exception: Exception) {
                if (methods.isEmpty()) {
                    throw exception
                }
            }
        }

        throw AddressNotFoundException()
    }
}