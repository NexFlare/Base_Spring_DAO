package com.nexflare.blog.helper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectToMap<T> {

    public ObjectToMap() {
    }

    public Map<String, Object> getMap(T object) {
        Map<String, Object> map = new HashMap<>();
        Field[] allFields = object.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
                if(value != null) map.put(field.getName(), value);
            } catch (IllegalAccessException ignore) {}
        }
        return map;
    }


}
