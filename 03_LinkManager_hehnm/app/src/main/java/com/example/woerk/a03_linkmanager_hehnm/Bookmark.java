package com.example.woerk.a03_linkmanager_hehnm;

public class Bookmark {
    String name;
    String address;

    //get
    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    //ctor
    public Bookmark(String name,String address){
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return name+" :"+address;
    }
}
