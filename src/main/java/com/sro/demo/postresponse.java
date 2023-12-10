package com.sro.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class postresponse {

    @JsonProperty
    String status;

    public postresponse(String status) {
        this.status = status;
    }
}
