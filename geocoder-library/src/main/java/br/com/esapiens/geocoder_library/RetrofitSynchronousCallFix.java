package br.com.esapiens.geocoder_library;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import br.com.esapiens.geocoder_library.entities.GoogleMapSearch;
import retrofit2.Response;

/**
 * The purpose of this class is solely to resolve a SIGV crash that was occurring on some devices if the Retrofit`s execute is called from Kotlin
 * If execute is called from Java, it works normally
 * Created by esapiens on 31/08/17.
 */
final class RetrofitSynchronousCallFix {
    @Nullable
    static Response<GoogleMapSearch> execute(MapsApi mapsApi, String latitudeAndLongitude) throws IOException {
        return mapsApi.getLocation(latitudeAndLongitude).execute();
    }
}
