package com.kienht.domain.usecase;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public abstract class FlowableUseCase<T> {

    private Scheduler threadExecutor;
    private Scheduler postExecutionThread;

    public FlowableUseCase(Scheduler threadExecutor, Scheduler postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }


    protected abstract Flowable<T> buildUseCaseObservable();

    public Disposable execute(DisposableSubscriber<T> disposableSubscriber) {
        return buildUseCaseObservable()
                .subscribeOn(threadExecutor)
                .observeOn(postExecutionThread)
                .subscribeWith(disposableSubscriber);
    }
}
