package com.nexflare.testhiber.mapper;

import com.nexflare.testhiber.pojo.AbstractDO;

public interface IDOToResponseMapper<K extends AbstractDO, V> {

    public V map(K obj);
}
