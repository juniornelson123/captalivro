package com.example.junior.captalivrosv2.domain;

import com.orm.SugarRecord;

/**
 * Created by junior on 22/05/2016.
 */

//Modelo da classe Usuario extendendo do SugarORM
public class User extends SugarRecord{
    private String username;
    private String password;
    private Boolean typeUser;
    private long userId;


    public User(){

    }

    public User(String username,String password,Boolean typeUser,long userId){
        this.setUsername(username);
        this.setPassword(password);
        this.setTypeUser(typeUser);
        this.setUser_id(userId);
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

    public Boolean getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(Boolean typeUser) {
        this.typeUser = typeUser;
    }

    public long getUserId() {
        return userId;
    }

    public void setUser_id(long userId) {
        this.userId = userId;
    }
}
