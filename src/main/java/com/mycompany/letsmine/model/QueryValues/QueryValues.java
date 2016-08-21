/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.model.QueryValues;

/**
 *
 * @author michaelfouche
 */
public class QueryValues {
    
    String actionValue;
    String hashtagValue;
    String lat;
    String lng;
    int radiusValue;
    String query;

    public QueryValues(String actionValue, String hashtagValue, String lat, String lng, int radiusValue, String query) {
        this.actionValue = actionValue;
        this.hashtagValue = hashtagValue;
        this.lat = lat;
        this.lng = lng;
        this.radiusValue = radiusValue;
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    
    

    public QueryValues() {
    }

    
    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    public String getHashtagValue() {
        return hashtagValue;
    }

    public void setHashtagValue(String hashtagValue) {
        this.hashtagValue = hashtagValue;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getRadiusValue() {
        return radiusValue;
    }

    public void setRadiusValue(int radiusValue) {
        this.radiusValue = radiusValue;
    }

        
    
    
}
