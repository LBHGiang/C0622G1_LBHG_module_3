package com.codegym.model;

import java.time.LocalDate;

public class Customer {
    private int id;
    private String name;
    private LocalDate birthDay;
    private String address;
    private String imgLink;

    public Customer() {
    }

    public Customer(int id, String name, LocalDate birthDay, String address, String imgLink) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.address = address;
        this.imgLink = imgLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
