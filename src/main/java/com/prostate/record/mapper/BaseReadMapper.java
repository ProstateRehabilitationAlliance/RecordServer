package com.prostate.record.mapper;

import java.util.List;

public interface BaseReadMapper<E>{

    E selectById(String id);

    List<E> selectByParams(E e);

}