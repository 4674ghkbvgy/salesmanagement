package com.dgut.entity;


public class User {

    private Integer id;
    private String name;
    private String password;
    private Integer type;
    private String email;
    private String phone;
    private String address;
    public User() {
    }
    public User(Integer id, String name, String password, Integer type) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public User(Integer id, String name, Integer type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public User( String name, String password, Integer type, String email, String phone, String address) {
        this.name = name;
        this.password = password;
        this.type = type;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public User(Integer id, String name, String password, Integer type, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }


    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}