package com.example.junior.captalivrosv2.domain;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by junior on 22/05/2016.
 */

//Modelo da classe Livro extendendo do SugarORM
public class Book extends SugarRecord implements Serializable{

    private byte[] image;
    private String title;
    private String author;
    private String year;
    private String serie;
    private String conserv;
    private double price;
    private User user;

    public Book(){

    }
    public Book(String title,String author,String year,String serie,String conserv,Double price,User user){
        this.setTitle(title);
        this.setAuthor(author);
        this.setYear(year);
        this.setSerie(serie);
        this.setConserv(conserv);
        this.setUser(user);
        this.setPrice(price);
        this.setImage(null);

    }
    public Book(String title,String author,String year,String serie,String conserv,Double price,User user,byte[] img){
        this.setTitle(title);
        this.setAuthor(author);
        this.setYear(year);
        this.setSerie(serie);
        this.setConserv(conserv);
        this.setPrice(price);
        this.setUser(user);
        this.setImage(img);


    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getConserv() {
        return conserv;
    }

    public void setConserv(String conserv) {
        this.conserv = conserv;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
