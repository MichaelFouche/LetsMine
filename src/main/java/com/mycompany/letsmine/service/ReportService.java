/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.service;

import com.mongodb.DBObject;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author michaelfouche
 */
public interface ReportService {
    public HashMap<String, Integer> getTagCloud(String query, String user);
    public List findByQuery(String field, DBObject dbObject, String collection);
}
