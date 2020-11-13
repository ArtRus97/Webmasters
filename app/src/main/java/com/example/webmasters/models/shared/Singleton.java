package com.example.webmasters.models.shared;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Singleton {
    private static final Singleton mInstance = new Singleton();

    @SuppressWarnings( "rawtypes")
    private final Map<Class, Object> mMap = new HashMap<>();

    private Singleton() { }


    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> classOf) throws InstantiationException, IllegalAccessException {
        synchronized(mInstance){
            if(!mInstance.mMap.containsKey(classOf)){
                T obj = classOf.newInstance();
                mInstance.mMap.put(classOf, obj);
            }
            return (T) mInstance.mMap.get(classOf);
        }

    }

    @NonNull
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

}
