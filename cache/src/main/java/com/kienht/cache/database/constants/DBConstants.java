package com.kienht.cache.database.constants;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class DBConstants {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RoomDemo";

    public interface Employee {
        String TABLE_NAME = "Employee";
        String COLUMN_ID = "id";
        String COLUMN_NAME = "name";
        String COLUMN_IMGURL = "imgUrl";
    }

}
