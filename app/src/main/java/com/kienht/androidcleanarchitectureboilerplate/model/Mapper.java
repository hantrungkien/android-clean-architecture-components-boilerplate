package com.kienht.androidcleanarchitectureboilerplate.model;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public interface Mapper<M, E> {
    E mapToViewModel(M type);
}
