package com.kienht.remote.mapper;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public interface Mapper<M, E> {
    E mapToEntity(M type);

    M mapToModel(E type);
}
