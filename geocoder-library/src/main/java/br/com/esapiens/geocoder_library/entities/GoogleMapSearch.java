package br.com.esapiens.geocoder_library.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esapiens on 21/08/17.
 */

public class GoogleMapSearch {
    private static final String OK_STATUS = "OK";

    @JsonProperty("results")
    private List<GoogleAddressResult> results = new ArrayList<>();

    @JsonProperty("status")
    private String status;

    public List<GoogleAddressResult> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }

    public boolean isOK() {
        return status.equals(OK_STATUS);
    }
}
