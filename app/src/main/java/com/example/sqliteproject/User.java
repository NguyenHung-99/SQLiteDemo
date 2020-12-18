package com.example.sqliteproject;

public class User {
    private int id;
    private String name;
    private int tuoi;

    public User(int id, String name, int tuoi) {
        this.id = id;
        this.name = name;
        this.tuoi = tuoi;
    }

    public User(String name, int tuoi) {
        this.name = name;
        this.tuoi = tuoi;
    }

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTuoi() {
        return tuoi;
    }
}
