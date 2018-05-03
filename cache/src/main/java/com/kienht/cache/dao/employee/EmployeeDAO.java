package com.kienht.cache.dao.employee;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.kienht.cache.dao.BaseDAO;
import com.kienht.cache.database.constants.DBConstants;
import com.kienht.cache.model.EmployeeCached;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Note:
 * Created by kienht on 4/26/18.
 */

@Dao
public abstract class EmployeeDAO extends BaseDAO<EmployeeCached> {

    @Query("SELECT * FROM " + DBConstants.Employee.TABLE_NAME + " WHERE id = :id")
    public abstract Flowable<EmployeeCached> getUserById(int id);

    @Query("SELECT * FROM " + DBConstants.Employee.TABLE_NAME)
    public abstract Flowable<List<EmployeeCached>> getUserList();
}
