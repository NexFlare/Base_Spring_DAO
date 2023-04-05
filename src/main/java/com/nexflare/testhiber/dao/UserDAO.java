package com.nexflare.testhiber.dao;

import com.nexflare.testhiber.pojo.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO extends AbstractDAO<User>{
    @Override
    public User get(User obj) {
        return super.get(obj);
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
                String statement = "from User where firstName = :firstName";
                query = getSession().createQuery(statement, User.class);
                query.setParameter("name", value);
                List<User> list = query.getResultList();
//                commit();
                return list;
            }
        }
        return null;
    }
}
