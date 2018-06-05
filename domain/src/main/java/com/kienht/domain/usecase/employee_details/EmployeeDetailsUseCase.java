package com.kienht.domain.usecase.employee_details;

import com.kienht.domain.model.EmployeeDomain;
import com.kienht.domain.usecase.SingleUseCase;

import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Note:
 * Created by kienht on 6/5/18.
 */
public class EmployeeDetailsUseCase extends SingleUseCase<EmployeeDomain, EmployeeDetailsUseCase.Params> {

    public EmployeeDetailsUseCase(Scheduler threadExecutor, Scheduler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Single<EmployeeDomain> buildUseCaseSingle(Params params) {
        return null;
    }

    public static final class Params {

        private final int id;

        public Params(int id) {
            this.id = id;
        }

        public static Params forEmployeeDetails(int id) {
            return new Params(id);
        }
    }
}
