/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.service;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mycompany.letsmine.model.TweetData;
import java.util.List;

/**
 *
 * @author michaelfouche
 */
public interface AnalyticsTagCloudService {
    public List findByField(String key);
    public List<TweetData> findByQuery(String query);
    public String conductTagCloudAnalytics(String query);
}
