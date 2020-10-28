package com.example.webmasters.ui;

public class WebStoreSingleton {
    // static variable single_instance of type Singleton
    private static WebStoreSingleton single_instance = null;

    // variable of type String
    public String desc;

    // private constructor restricted to this class itself
    private WebStoreSingleton() {
        desc = "Hello I am a string part of Singleton class";
    }

    // static method to create instance of Singleton class
    public static WebStoreSingleton Singleton() {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new WebStoreSingleton();
        }
        return single_instance;
    }
}
