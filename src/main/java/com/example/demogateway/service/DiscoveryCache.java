package com.example.demogateway.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class DiscoveryCache {

    HashMap<String,String> discoveryMap;

    public Map getCache(){
        return this.discoveryMap;
    }

    public String get(String key){
        if(discoveryMap.containsKey(key)){
            return discoveryMap.get(key);
        }
        return null;
    }

    public void set(String key, String value){
        discoveryMap.put(key, value);
    }

}
