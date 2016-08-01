/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.config;
import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
/**
 *
 * @author michaelfouche
 */
@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {

    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
            return new SimpleMongoDbFactory(new MongoClient(), "LetsMine");
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {

            MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

            return mongoTemplate;

    }

    @Override
    public String getDatabaseName() {
            return "LetsMine";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
            return new MongoClient("127.0.0.1");
    }
}