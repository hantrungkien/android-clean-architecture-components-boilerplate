package com.kienht.remote.model;

/**
 * Note:
 * Created by kienht on 4/30/18.
 */
public class EmployeeRemote {
    private int id;
    private String name;
    private String imgUrl;

    public EmployeeRemote(int id, String name, String imgUrl) {
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
