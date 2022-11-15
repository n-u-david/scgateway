package com.example.demogateway.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class TokenCache {

    HashMap<String, String> tokenMap;

    public TokenCache(){
        if(tokenMap==null){
            tokenMap = new HashMap<>();
        }
    }

    public HashMap getCache(){
        return this.tokenMap;
    }

    public String get(String key){
        if(tokenMap.containsKey(key)){
            return tokenMap.get(key);
        }
        return null;
    }

    public void set(String key, String value){
        tokenMap.put(key, value);
    }

}
