package com.tour.prevel.tourapi.dto;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ApiRequest {
    protected final String MobileOS = "ETC";
    protected final String MobileApp = "Prevel";
    protected final String _type = "json";

    protected String serviceKey;

    public String toQueryParameters() {
        List<String> params = new ArrayList<>();
        Class<?> clazz = this.getClass();
        while (clazz != null) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                try {
                    Object value = field.get(this);
                    if (value != null) {
                        params.add(field.getName() + "=" + value);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error while creating query parameters", e);
                }
            }
            clazz = clazz.getSuperclass();
        }

        return String.join("&", params);
    }
}
