package com.kienht.domain.di.scheduler;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RunOn {
    SchedulerType value() default SchedulerType.IO;
}
