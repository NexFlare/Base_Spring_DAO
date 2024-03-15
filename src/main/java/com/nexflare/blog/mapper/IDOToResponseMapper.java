package com.nexflare.blog.mapper;

import com.nexflare.blog.pojo.AbstractDO;

public interface IDOToResponseMapper<K extends AbstractDO, V> {

    public V map(K obj);
}
