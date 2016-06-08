package com.example.junior.captalivrosv2.domain;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by junior on 22/05/2016.
 */

//Modelo da classe Vendedor extendendo do SugarORM
public class Seller extends SugarRecord {
    private byte[] image;
    private String boxDescription;
    private String name;
    private String cnpj;
    private String phone;
    private String email;



    public Seller(){

    }


    public Seller(String boxDescription,String name,String cnpj,String phone,String email){
        this.setBoxDescription(boxDescription);
        this.setName(name);
        this.setCnpj(cnpj);
        this.setPhone(phone);
        this.setEmail(email);
        this.setImage(null);
    }

    public Seller(String boxDescription,String name,String cnpj,String phone,String email,byte[] image){
        this.setBoxDescription(boxDescription);
        this.setName(name);
        this.setCnpj(cnpj);
        this.setPhone(phone);
        this.setEmail(email);
        this.setImage(image);
    }

    public String getBoxDescription() {
        return boxDescription;
    }

    public void setBoxDescription(String boxDescription) {
        this.boxDescription = boxDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Book> getBooks(){
        return Book.findWithQuery(Book.class,"Select * from Book where seller=?",this.getId().toString());
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
