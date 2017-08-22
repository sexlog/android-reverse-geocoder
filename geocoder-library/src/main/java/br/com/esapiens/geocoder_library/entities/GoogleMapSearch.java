package br.com.esapiens.geocoder_library.entities;

import java.util.List;

/**
 * Created by esapiens on 21/08/17.
 */

public class GoogleMapSearch {
    private static final String OK_STATUS = "OK";

    private List<GoogleAddressResult> results;
    private String status;

    public List<GoogleAddressResult> getResults() {
        return results;
    }

    public void setResults(List<GoogleAddressResult> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOK() {
        return status.equals(OK_STATUS);
    }
}
