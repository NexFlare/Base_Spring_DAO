package com.nexflare.testhiber.mapper;

import com.nexflare.testhiber.pojo.AbstractDO;
import com.nexflare.testhiber.requestModel.AbstractRequestObject;

public interface IRequestToDOMapper<K extends AbstractRequestObject,V extends AbstractDO> {

    public V map(K obj);
}
