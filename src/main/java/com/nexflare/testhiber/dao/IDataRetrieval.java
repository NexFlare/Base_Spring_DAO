package com.nexflare.testhiber.dao;

import java.util.List;

public interface IDataRetrieval<K> {

    K get(K obj);

    List<K> getAll();

    void update(K obj);

    void delete(K obj);


    void add(K obj);
}
