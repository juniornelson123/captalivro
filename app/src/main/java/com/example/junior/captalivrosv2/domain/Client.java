package com.example.junior.captalivrosv2.domain;

import com.orm.SugarRecord;

/**
 * Created by junior on 22/05/2016.
 */

//Modelo da classe Cliente extendendo do SugarORM
public class Client extends SugarRecord {
    private String name;
    private String email;

    public Client(){

    }

    public Client(String name,String email){
        this.setName(name);
        this.setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
