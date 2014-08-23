package com.adarrivi.multi;

class RequestWithNoInjectParam implements IdContainer {


    private String rqId;

    RequestWithNoInjectParam(String rqId) {
        this.rqId = rqId;
    }

    @Override
    public String getId() {
        return rqId;
    }
}
