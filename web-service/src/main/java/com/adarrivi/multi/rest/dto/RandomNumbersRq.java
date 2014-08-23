package com.adarrivi.multi.rest.dto;

import com.adarrivi.multi.IncludedIntoLogContext;

public class RandomNumbersRq {

    @IncludedIntoLogContext
    private String rqId;

    public String getRqId() {
        return rqId;
    }

    public void setRqId(String rqId) {
        this.rqId = rqId;
    }
}
