package com.example.demogateway.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class DiscoveryCache {

    HashMap<String,String> discoveryMap;
    public DiscoveryCache() {
        if(discoveryMap==null){
            discoveryMap = new HashMap<>();
        }
    }


    public HashMap<String,String> getCache(){
        return this.discoveryMap;
    }

    public String get(String key){
        if(this.discoveryMap.containsKey(key)){
            return this.discoveryMap.get(key);
        }
        return null;
    }

    public void set(String key, String value){
        this.discoveryMap.put(key, value);
    }

}
