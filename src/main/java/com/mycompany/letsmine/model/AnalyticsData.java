/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.model;

import java.util.HashMap;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author michaelfouche
 */
@Document(collection = "AnalyticsData")
public class AnalyticsData {
    
    HashMap<String, Integer> tagCloudHashMap;
    String searchQuery;
    String user;

    public AnalyticsData() {
        //hibernate
    }

    public AnalyticsData(HashMap<String, Integer> tagCloudHashMap) {
        this.tagCloudHashMap = tagCloudHashMap;
    }

    public String getQuery() {
        return searchQuery;
    }

    public void setQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    
    
    
    public HashMap<String, Integer> getTagCloudHashMap() {
        return tagCloudHashMap;
    }

    public void setTagCloudHashMap(HashMap<String, Integer> tagCloudHashMap) {
        this.tagCloudHashMap = tagCloudHashMap;
    }
    
    
    
    
    
}
