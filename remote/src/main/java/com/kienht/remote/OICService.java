package com.kienht.remote;

import com.kienht.remote.response.EmployeeListResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface OICService {

    String URL_SERVER = "http://82.115.100.250:8989/sendbyfriend/rest/";

    @GET("/getInformation")
    Single<EmployeeListResponse> getInformation();
}
