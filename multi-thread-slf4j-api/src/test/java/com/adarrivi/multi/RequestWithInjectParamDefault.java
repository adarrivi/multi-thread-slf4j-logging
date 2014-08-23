package com.adarrivi.multi;

class RequestWithInjectParamDefault implements IdContainer {


    @IncludedIntoLogContext
    private String rqId;

    RequestWithInjectParamDefault(String rqId) {
        this.rqId = rqId;
    }

    @Override
    public String getId() {
        return rqId;
    }
}
