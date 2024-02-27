package com.example.prestigeportfoliocreators.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class Response {
    private Response(){}

    public static HashMap<String, Object> createBody() {
        return createBody(new String[] {}, new Object[] {});
    }

    public static HashMap<String, Object> createBody(String key, Object value) {
        return createBody(new String[] {key}, new Object[] {value});
    }

    public static HashMap<String, Object> createBody(String[] keys, Object[] values) {
        if (keys.length != values.length)
            return null;
        HashMap<String, Object> res = new HashMap<>();
        res.put("status", "success");
        for (int i = 0; i < keys.length; i++) {
            res.put(keys[i], values[i]);
        }
        return res;
    }

    public static ResponseEntity<HashMap<String, Object>> errorMessage(String reason, HttpStatus status) {
        String[] keys = {"status", "reason"};
        String[] values = {"error", reason};
        return new ResponseEntity<>(createBody(keys, values), status);
    }
}
