package com.kienht.cache.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.kienht.cache.dao.employee.EmployeeDAO;
import com.kienht.cache.model.EmployeeCached;

import static com.kienht.cache.database.constants.DBConstants.DATABASE_VERSION;

/**
 * Note:
 * Created by kienht on 4/26/18.
 */

@Database(entities = EmployeeCached.class, version = DATABASE_VERSION, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    public abstract EmployeeDAO employeeDAO();

}
