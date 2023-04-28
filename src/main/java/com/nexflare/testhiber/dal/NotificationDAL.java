package com.nexflare.testhiber.dal;

import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.Notification;
import com.nexflare.testhiber.pojo.User;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class NotificationDAL extends AbstractDAL<Notification, UUID> {
    @Override
    protected List<Notification> _getElementsByQuery(Map<String, Object> map) {
        org.hibernate.query.Query q = getQuery("Notification", map);
        List<Notification> notifications = q.getResultList();
        return notifications;
    }

    @Override
    protected Notification _getUniqueElementByQuery(Map<String, Object> map) {
        Query query = this.getQuery("Notification", map);
        try{
            Notification notification = (Notification) query.getSingleResult();
            return notification;
        } catch(NoResultException exception) {
            throw new DataNotFoundException(exception.getMessage());
        }
    }

    @Override
    protected List<Notification> _getAll() {
        String s = "FROM Notification";
        Query query = getSession().createQuery(s);
        List<Notification> list = query.getResultList();
        return list;
    }
}
