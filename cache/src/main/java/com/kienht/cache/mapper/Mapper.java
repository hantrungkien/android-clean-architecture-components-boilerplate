package com.kienht.cache.mapper;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public interface Mapper<C, E> {

    E mapFromCached(C type);

    C mapToCached(E type);

}
