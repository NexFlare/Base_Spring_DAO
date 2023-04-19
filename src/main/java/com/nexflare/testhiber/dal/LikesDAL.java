package com.nexflare.testhiber.dal;

import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.pojo.Likes;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class LikesDAL extends AbstractDAL<Likes, UUID> {


    @Override
    public List<Likes> _getAll() throws DataNotFoundException {
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
    public List<Likes> _getElementsByQuery(Map<String, Object> map) {
        return null;
    }

    @Override
    public Likes _getUniqueElementByQuery(Map<String, Object> map) {
        Query q = getQuery("Likes", map);
        try{
            Likes like = (Likes) q.getSingleResult();
            return like;
        } catch(Exception e) {
            throw new DataNotFoundException("Data not found");
        }


    }
}
