package com.example.memorizeword;

public class Word {

    String name;
    String yazarname;
    int id;

    public Word() {
    }

    public Word(String name, String yazarname, int id) {
        this.name = name;
        this.yazarname = yazarname;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYazarname() {
        return yazarname;
    }

    public void setYazarname(String yazarname) {
        this.yazarname = yazarname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
