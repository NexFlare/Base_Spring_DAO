package com.nexflare.testhiber.dal;

import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.Comments;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class CommentDAL extends AbstractDAL<Comments, UUID> {
    @Override
    public Comments get(UUID id) throws DataNotFoundException {
        begin();
        Query q = getSession().createQuery("from Comments where id = :id ");
        q.setParameter("id", id);
        try{
            Comments commentObj =(Comments) q.getSingleResult();
            commit();
            if(commentObj == null) throw new DataNotFoundException("Comment not found");
            return commentObj;
        } catch(NoResultException e) {
            throw new DataNotFoundException("Comment not found");
        }
    }

    @Override
    public List<Comments> getElementByQuery(String property, String value) {
        return null;
    }

    @Override
    public List<Comments> getElementsByQuery(Map<String, Object> map) {
        org.hibernate.query.Query q = getQuery("Comments", map);
        List<Comments> comments = q.getResultList();
        return comments;
    }

    @Override
    public Comments getUniqueElementByQuery(Map<String, Object> map) {
        return null;
    }
}
