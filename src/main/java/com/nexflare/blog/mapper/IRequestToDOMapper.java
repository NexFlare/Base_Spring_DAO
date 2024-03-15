package com.nexflare.blog.mapper;

import com.nexflare.blog.pojo.AbstractDO;
import com.nexflare.blog.requestModel.AbstractRequestObject;

public interface IRequestToDOMapper<K extends AbstractRequestObject,V extends AbstractDO> {

    public V map(K obj);
}
