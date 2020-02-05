package com.nith.appteam.nimbus2020.Models;

public class Id_Value {
    private String value;
    private String id;
    private String imageUrl;

    public Id_Value(String value, String id, String imageUrl) {
        this.value = value;
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }
}
