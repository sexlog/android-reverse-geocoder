package br.com.esapiens.geocoder_library;

import br.com.esapiens.geocoder_library.entities.GoogleMapSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by esapiens on 21/08/17.
 */

interface MapsApi {
    @GET("geocode/json?sensor=true")
    Call<GoogleMapSearch> getLocation(@Query("latlng") String latitudeAndLongitude);
}
