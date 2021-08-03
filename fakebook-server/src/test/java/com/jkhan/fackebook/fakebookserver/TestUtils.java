package com.jkhan.fackebook.fakebookserver;

public class TestUtils {
    private static String baseUrl = "http://localhost:8080";
    public static String buildUrl(String endpoint) {
        if (endpoint.charAt(0) == '/') {
            return baseUrl + endpoint;
        } else {
            return baseUrl + "/" + endpoint;
        }
    }
}
