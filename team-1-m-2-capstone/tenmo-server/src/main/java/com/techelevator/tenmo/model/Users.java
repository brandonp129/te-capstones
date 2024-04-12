package com.techelevator.tenmo.model;

public class Users {

    private int id;
    private String username;

    public Users(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
