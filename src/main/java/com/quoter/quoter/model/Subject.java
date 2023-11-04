package com.quoter.quoter.model;

public class Subject {
    Long id;
    String name;

    public Subject() {

    }

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
