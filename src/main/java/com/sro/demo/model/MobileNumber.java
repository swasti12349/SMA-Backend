package com.sro.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MobileNumber {

    @JsonProperty
    String mobile;

    public MobileNumber(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
