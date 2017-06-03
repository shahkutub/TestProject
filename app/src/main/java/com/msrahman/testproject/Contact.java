package com.msrahman.testproject;

/**
 * Created by User on 12/26/2016.
 */

public class Contact {

    //private variables
    int id;
    String name;
    String age;
    String lat;
    String lon;

    public Contact() {
    }

    public Contact(String name, String age, String lat, String lon) {
        this.name = name;
        this.age = age;
        this.lat = lat;
        this.lon = lon;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}