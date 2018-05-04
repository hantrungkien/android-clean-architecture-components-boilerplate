package com.kienht.remote;

import com.kienht.remote.response.EmployeeListResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface OICService {

    String URL_SERVER = "http://oicsoft.com/api_v1/";

    @GET("/emoployee_list")
    Single<EmployeeListResponse> getEmployeeList();
}
