package com.kienht.cache.mapper;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public interface Mapper<E, V> {

    V mapFromCached(E type);

    E mapToCached(V type);

}
