package com.kienht.cache.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.kienht.cache.database.constants.DBConstants;

/**
 * Note:
 * Created by kienht on 4/26/18.
 */

@Entity(tableName = DBConstants.Employee.TABLE_NAME)
public class EmployeeCache {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBConstants.Employee.COLUMN_ID)
    private int id;

    @ColumnInfo(name = DBConstants.Employee.TABLE_NAME)
    private String name;

    @ColumnInfo(name = DBConstants.Employee.COLUMN_IMGURL)
    private String imgUrl;

    public EmployeeCache(int id, String name, String imgUrl) {
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
