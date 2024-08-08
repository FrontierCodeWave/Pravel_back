package com.tour.prevel.tourapi.domain;

public enum ContentTypeId {
    TOUR("12"),
    RESTAURANT("39")
    ;

    private String id;

    ContentTypeId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
