package com.learning.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;

public class Convertor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertObjectToString(Object object) {
        try{
            if(ObjectUtils.allNotNull(object)){
                if(isAnyTypeOf(object, String.class, Number.class)){
                    return object.toString();
                }
                String json = objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(object);
                return json;
            }
        } catch(Exception e){
            throw new RuntimeException("Unable to convert ", e);
        }
        return "";
    }

    private static boolean isAnyTypeOf(Object object, Class<?>... types) {
        for (Class<?> type : types) {
            if(type.isInstance(object)){
                return true;
            }
        }
        return false;
    }
}
