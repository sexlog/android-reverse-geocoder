package br.com.esapiens.geocoder_library.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esapiens on 21/08/17.
 */

public class GoogleAddressResult {
    @JsonProperty("formatted_address")
    private String formattedAdress;

    @JsonProperty("address_components")
    private List<AddressComponent> addressComponents = new ArrayList<>();

    @JsonProperty("types")
    private List<String> types = new ArrayList<>();

    public String getFormattedAdress() {
        return formattedAdress;
    }

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    /**
     * Return all the address component types this result has
     * Check the AddressType class for the available types
     * @return A list containing all the types this result has
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * Check if this result contains an specific Address Type
     * If you want to view all the available types, see the AddressType class
     * @param addressComponentType The address component type you want to check, See the available types on the AddressType class
     * @return True if this address contains the type, False otherwise
     */
    public boolean containsType(String addressComponentType) {
        return types.contains(addressComponentType);
    }
}
