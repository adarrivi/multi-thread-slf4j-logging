package com.adarrivi.multi;

class RequestWithInjectParam implements IdContainer {

    @IncludedIntoLogContext(key = "rqId")
    private String requestId;

    RequestWithInjectParam(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String getId() {
        return requestId;
    }
}
