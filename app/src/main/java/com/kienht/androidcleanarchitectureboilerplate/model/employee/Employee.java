package com.kienht.androidcleanarchitectureboilerplate.model.employee;

/**
 * Note:
 * Created by kienht on 5/3/18.
 */
public class Employee {

    private int id;
    private String name;
    private String imgUrl;

    public Employee(int id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
