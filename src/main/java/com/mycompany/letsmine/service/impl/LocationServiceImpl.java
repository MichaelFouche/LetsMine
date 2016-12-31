/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.service.impl;

import com.mycompany.letsmine.geoCode.AddressConverter;
import com.mycompany.letsmine.geoCode.GoogleResponse;
import com.mycompany.letsmine.geoCode.Result;
import com.mycompany.letsmine.service.LocationService;

/**
 *
 * @author michaelfouche
 */
public class LocationServiceImpl implements LocationService{

    @Override
    public String[] getLocationLatLong(String address) {
        try{
            String lat = "";
            String lng = "";

            String[] latlng = new String[2];
            try
            {
                GoogleResponse res = new AddressConverter().convertToLatLong(address);
                if(res.getStatus().equals("OK"))
                {
                     for(Result result : res.getResults())
                     {
                         lat = result.getGeometry().getLocation().getLat();
                         lng = result.getGeometry().getLocation().getLng();
                         latlng[0] = lat;
                         latlng[1] = lng;
             //            System.out.println("Lattitude of address is :"  +lat);
             //            System.out.println("Longitude of address is :" + lng);
             //            System.out.println("Location is " + result.getGeometry().getLocation_type());
                     }
                }
                else
                {
                     System.out.println(res.getStatus());
                }
            }
            catch(Exception e)
            {
                System.out.println("ERROR: Retrieving location servlet: "+e);
            }
            return latlng;
        }
        catch(Exception e)
        {
            System.out.println("Error "+e);
        }
        return null;
    }
    
}
