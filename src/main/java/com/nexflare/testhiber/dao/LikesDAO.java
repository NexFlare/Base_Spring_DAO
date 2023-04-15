package com.nexflare.testhiber.dao;

import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.Likes;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class LikesDAO extends AbstractDAO<Likes, UUID>{


    @Override
    public List<Likes> getAll() throws DataNotFoundException {
        String s = "FROM Likes";
        Query query = getSession().createQuery(s);
        List<Likes> list = query.list();
        return list;
    }

    @Override
    public List<Likes> getElementByQuery(String property, String value) {
        return null;
    }

    @Override
    public List<Likes> getElementsByQuery(Map<String, Object> map) {
        return null;
    }

    @Override
    public Likes getUniqueElementByQuery(Map<String, Object> map) {
        Query q = getQuery("Likes", map);
        Likes like = (Likes) q.getSingleResult();
        return like;
    }
}
