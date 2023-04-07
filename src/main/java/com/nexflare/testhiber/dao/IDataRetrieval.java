package com.nexflare.testhiber.dao;

import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.pojo.AbstractDO;

import java.util.List;

public interface IDataRetrieval<K extends AbstractDO, T> {

    K get(T id) throws DataNotFoundException;

    List<K> getAll() throws DataNotFoundException;

    void update(K obj);

    void delete(K obj);


    void add(K obj);
}
