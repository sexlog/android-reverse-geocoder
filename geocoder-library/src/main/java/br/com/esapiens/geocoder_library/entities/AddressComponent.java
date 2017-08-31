package br.com.esapiens.geocoder_library.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esapiens on 31/08/17.
 */

public class AddressComponent {
    @JsonProperty("long_name")
    private String longName;

    @JsonProperty("short_name")
    private String shortName;

    @JsonProperty("types")
    private List<String> types = new ArrayList<>();

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }

    /**
     * Return all the address component types this component has
     * Check the AddressType class for the available types
     * @return A list containing all the types this component has
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * Check if this address component contains an specific Address Type
     * If you want to view all the available types, see the AddressType class
     * @param addressComponentType The address component type you want to check, See the available types on the AddressType class
     * @return True if this address contains the type, False otherwise
     */
    public boolean containsType(String addressComponentType) {
        return types.contains(addressComponentType);
    }
}
