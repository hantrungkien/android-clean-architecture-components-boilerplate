package com.kienht.remote.features.employee_list;

import com.kienht.data.model.EmployeeEntity;
import com.kienht.data.repository.employee.EmployeeRemote;
import com.kienht.remote.OICService;
import com.kienht.remote.mapper.employee.EmployeeModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */

@Singleton
public class EmployeeRemoteImpl implements EmployeeRemote {

    @Inject
    OICService oicService;

    @Inject
    EmployeeModelMapper EmployeeModelMapper;

    @Inject
    public EmployeeRemoteImpl() {
    }

    @Override
    public Flowable<List<EmployeeEntity>> getEmployees() {
        return Single.create((SingleOnSubscribe<List<EmployeeEntity>>) e -> {
            if (!e.isDisposed()) {
                List<EmployeeEntity> employeeEntityList = new ArrayList<EmployeeEntity>() {{
                    add(new EmployeeEntity(1, "KienHT", "https://scontent.fhan5-1.fna.fbcdn.net/v/t1.0-9/28279472_1622239054523467_2397255532494660807_n.jpg?_nc_cat=0&_nc_eui2=v1%3AAeE6VisXUVvoaOlb4rgtkUoND2X2Nx7a46L305HwUdRwgRnb0wYplvOLrGTuUq0kESc9goVUxUDS0ZE7IlMbPfsJSfkZQWqRnQjk7u5M4D0kwg&oh=d58754b7732a76ceaf89abcc6a6de60e&oe=5B4F5BEC"));
                    add(new EmployeeEntity(2, "HuyKN", "https://scontent.fhan5-1.fna.fbcdn.net/v/t31.0-8/14124884_633012950191077_2876234194035778629_o.jpg?_nc_cat=0&_nc_eui2=v1%3AAeFvhR8M6GUug05wLhdZOiOqMlDVKK3JA3nf35zc-32xkR8HY6o_hU0Jw5pRj0LLhh3Ff8d4IM1Z1WVlNKOb6JbajI1YErG2pUl5NnNXxohq0A&oh=c408de042be28337cd6a21cba3948369&oe=5B6132C2"));
                    add(new EmployeeEntity(3, "KhacPV", "https://scontent.fhan5-1.fna.fbcdn.net/v/t1.0-9/35010_590807984264116_171137885_n.jpg?_nc_cat=0&_nc_eui2=v1%3AAeHuYD3oi191pveWtNx4pnszA_NF4CsQFj68P05hUJ3qHiztqJyLkaYm_bQR6ucnJIT7PkEqhUgJdS2H6A919TjLrhz1FairKgYU0HN8r-Pwtw&oh=63a4b3858511467c9a3e5ede647928a7&oe=5B607936"));
                }};

                e.onSuccess(employeeEntityList);
            }
        }).toFlowable()
                .delay(3, TimeUnit.SECONDS);
    }
}
