package com.kienht.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kienht.remote.model.EmployeeModel;

import java.util.List;

/**
 * Note:
 * Created by kienht on 4/30/18.
 */
public class EmployeeListResponse extends BaseResponse{

    @SerializedName("data")
    @Expose
    private List<EmployeeModel> employeeModelList;

    public List<EmployeeModel> getEmployeeRemoteList() {
        return employeeModelList;
    }

    public void setEmployeeRemoteList(List<EmployeeModel> employeeModelList) {
        this.employeeModelList = employeeModelList;
    }
}
