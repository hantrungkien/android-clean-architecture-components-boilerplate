package com.kienht.remote.features.employee;

import com.kienht.data.model.EmployeeEntity;
import com.kienht.data.repository.employee.EmployeeRemote;
import com.kienht.remote.OICService;
import com.kienht.remote.mapper.employee.EmployeeMapper;
import com.kienht.remote.model.EmployeeModel;
import com.kienht.remote.response.EmployeeListResponse;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeRemoteImpl implements EmployeeRemote {

    private OICService oicService;
    private EmployeeMapper employeeMapper;

    @Inject
    public EmployeeRemoteImpl(OICService oicService, EmployeeMapper employeeMapper) {
        this.oicService = oicService;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Flowable<List<EmployeeEntity>> getEmployees() {
        return Single.create((SingleOnSubscribe<List<EmployeeEntity>>) e -> {
            if (!e.isDisposed()) {
                List<EmployeeEntity> employeeEntityList = new ArrayList<EmployeeEntity>() {{
                    add(new EmployeeEntity(0, "KienHT", ""));
                    add(new EmployeeEntity(1, "KhacPV", ""));
                    add(new EmployeeEntity(1, "HuyKN", ""));
                }};

                e.onSuccess(employeeEntityList);
            }
        }).toFlowable().delay(5, TimeUnit.SECONDS);
    }
}
