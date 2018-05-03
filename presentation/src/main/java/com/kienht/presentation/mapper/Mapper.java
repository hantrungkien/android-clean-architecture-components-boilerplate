package com.kienht.presentation.mapper;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public interface Mapper<T, E> {

    E mapToView(T type);

}
