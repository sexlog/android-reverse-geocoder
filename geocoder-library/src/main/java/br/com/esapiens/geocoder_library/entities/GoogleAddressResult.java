package br.com.esapiens.geocoder_library.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by esapiens on 21/08/17.
 */

public class GoogleAddressResult {
    @JsonProperty("formatted_address")
    private String formattedAdress;

    public String getFormattedAdress() {
        return formattedAdress;
    }

    public void setFormattedAdress(String formattedAdress) {
        this.formattedAdress = formattedAdress;
    }
}
