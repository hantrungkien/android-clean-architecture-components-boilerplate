package com.kienht.remote.features.employee;

import com.kienht.data.model.EmployeeData;
import com.kienht.data.repository.employee.EmployeeRemote;
import com.kienht.remote.OICService;
import com.kienht.remote.mapper.employee.EmployeeRemoteMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeRemoteImpl implements EmployeeRemote {

    private OICService oicService;
    private EmployeeRemoteMapper EmployeeRemoteMapper;

    @Inject
    public EmployeeRemoteImpl(OICService oicService, EmployeeRemoteMapper EmployeeRemoteMapper) {
        this.oicService = oicService;
        this.EmployeeRemoteMapper = EmployeeRemoteMapper;
    }

    @Override
    public Flowable<List<EmployeeData>> getEmployees() {
        return Single.create((SingleOnSubscribe<List<EmployeeData>>) e -> {
            if (!e.isDisposed()) {
                List<EmployeeData> employeeDataList = new ArrayList<EmployeeData>() {{
                    add(new EmployeeData(1, "KienHT", ""));
                    add(new EmployeeData(2, "KhacPV", ""));
                    add(new EmployeeData(3, "HuyKN", ""));
                }};

                e.onSuccess(employeeDataList);
            }
        }).toFlowable().delay(3, TimeUnit.SECONDS);
    }
}
