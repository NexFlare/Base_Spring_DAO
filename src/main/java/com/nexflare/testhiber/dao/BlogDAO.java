package com.nexflare.testhiber.dao;

import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BlogDAO extends AbstractDAO<Blog, UUID>{

    @Override
    public Blog get(UUID id) throws DataNotFoundException {
        begin();
        Query q = getSession().createQuery("from Blog where blogId = :id ");
        q.setParameter("id", id);
        Blog blog =(Blog) q.uniqueResult();
        commit();
        return blog;
    }

    @Override
    public List<Blog> getAll() throws DataNotFoundException {
        String s = "FROM Blog";
        Query query = getSession().createQuery(s);
        List<Blog> list = query.list();
        return list;
    }

    @Override
    public List<Blog> getElementByQuery(String property, String value) {
        return null;
    }
}
