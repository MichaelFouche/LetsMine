/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author michaelfouche
 */

@Document(collection = "users")
public class User {

	@Id
	private String id;

	String username;

	String password;
        
        List<String> queries;
	

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    

    public User(String username, String password, List<String> queries) {
        this.username = username;
        this.password = password;
        this.queries = queries;
    }



    public List<String> getQueries() {
        return queries;
    }

    public void setQueries(List<String> queries) {
        this.queries = queries;
    }

    @Override
    public String toString() {
        return "username=" + username + ", queries=" + queries + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}