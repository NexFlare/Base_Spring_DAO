package com.nexflare.testhiber.dao;

import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;

@Component
public class UserDAO extends AbstractDAO<User, UUID>{
    @Override
    public User get(UUID id) throws DataNotFoundException {

            begin();
            Query q = getSession().createQuery("from User where id = :id ");
            q.setParameter("id", id);
            User user =(User) q.uniqueResult();
            commit();
            if(user == null) throw new DataNotFoundException("User not found");
            return user;

    }

    @Override
    public List<User> getAll() {
        String s = "FROM User";
        Query query = getSession().createQuery(s);
        List<User> list = query.list();
        return list;
    }

    @Override
    public List<User> getElementByQuery(String property, String value) {
        final Query<User> query;
        switch (property) {
            case "email" -> {
                String statement = "from User where email = :email";
                query = getSession().createQuery(statement, User.class);
                query.setParameter("email", value);
                List<User> list = query.list();
//                commit();
                return list;
            }
            case "firstName" -> {
                String statement = "from User where firstName = :firstname";
                query = getSession().createQuery(statement, User.class);
                query.setParameter("firstname", value);
                List<User> list = query.getResultList();
//                commit();
                return list;
            }
        }
        return null;
    }
}
