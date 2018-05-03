package com.kienht.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kienht.remote.model.EmployeeRemote;

import java.util.List;

/**
 * Note:
 * Created by kienht on 4/30/18.
 */
public class EmployeeListResponse extends BaseResponse{

    @SerializedName("data")
    @Expose
    private List<EmployeeRemote> employeeRemoteList;

    public List<EmployeeRemote> getEmployeeRemoteList() {
        return employeeRemoteList;
    }

    public void setEmployeeRemoteList(List<EmployeeRemote> employeeRemoteList) {
        this.employeeRemoteList = employeeRemoteList;
    }
}
