package com.nexflare.blog.dal;

import com.nexflare.blog.exceptions.DataNotFoundException;
import com.nexflare.blog.pojo.AbstractDO;

import java.util.List;

public interface IDataRetrieval<K extends AbstractDO, T> {

    K get(T id) throws DataNotFoundException;

    List<K> getAll() throws DataNotFoundException;

    void update(K obj);

    void delete(K obj);

    int bulkDelete(List<K> objArray);

    void add(K obj);
}
