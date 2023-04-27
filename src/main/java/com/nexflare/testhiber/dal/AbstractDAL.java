package com.nexflare.testhiber.dal;

import com.nexflare.testhiber.exceptions.DataNotFoundException;
import com.nexflare.testhiber.exceptions.DatabaseException;
import com.nexflare.testhiber.pojo.AbstractDO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDAL<K extends AbstractDO, T> implements IDataRetrieval<K, T>{

    private static final Logger log = Logger.getAnonymousLogger();
    private static final ThreadLocal<Session> sessionThread = new ThreadLocal<>();
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static Session getSession() {
        if(sessionThread.get() == null) {
            Session s =  AbstractDAL.sessionFactory.openSession();
            sessionThread.set(s);
        }
        return sessionThread.get();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        AbstractDAL.sessionThread.set(null);
    }


    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    public static void close() {
        getSession().close();
        AbstractDAL.sessionThread.set(null);
    }
    @Override
    public K get(T id) throws DataNotFoundException {
      return null;
    }

    @Override
    public List<K> getAll() throws DataNotFoundException {
        try{
            return _getAll();
        } catch(Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void update(K obj) {
        try {
            begin();
            getSession().merge(obj);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            log.log(Level.SEVERE, e.getMessage());
            System.out.println("Error has occurred update" + e);
            throw new DatabaseException(e.getMessage());
            //throw new AdException("Could not create the category", e);
//            throw new CategoryException("Exception while deleting category: " + e.getMessage());
        }
    }

    @Override
    public void delete(K obj) {
        try {
            //save category to the database
            begin();
            getSession().remove(obj);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new DatabaseException(e.getMessage());
            //throw new AdException("Could not create the category", e);
//            throw new CategoryException("Exception while deleting category: " + e.getMessage());
        }
    }

    @Override
    public int bulkDelete(List<K> objArray) {
        int count = 0;
        try {
            begin();
            for(int i=0;i<objArray.size();i++) {
                getSession().remove(objArray.get(i));
                count++;
                if(i%50 == 0) {
                    getSession().flush();
                    getSession().clear();
                }
            }
            commit();
            close();
        } catch(HibernateException e) {
            rollback();
            throw new DatabaseException(e.getMessage());
        }
        return count;
    }

    @Override
    public void add(K obj) {
        try {
            //save category to the database
            begin();
            getSession().save(obj);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            log.log(Level.SEVERE, e.getMessage());
            throw new DatabaseException("Invalid data provided");
        }
    }
    public List<K> getElementsByQuery(Map<String, Object> map) {
        try{
            begin();
            List<K> list = _getElementsByQuery(map);
            commit();
            close();
            return list;
        } catch(Exception e) {
            rollback();
            log.log(Level.SEVERE,e.getMessage());
            throw new DatabaseException(e.getMessage());
        }

    }

    public K getUniqueElementByQuery(Map<String, Object> map) {
        try{
            begin();
            K element = _getUniqueElementByQuery(map);
            commit();
            close();
            return element;
        } catch(Exception e) {
            rollback();
            log.log(Level.SEVERE,e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
    }

    protected abstract List<K> _getElementsByQuery(Map<String, Object> map);

    protected abstract K _getUniqueElementByQuery(Map<String, Object> map);

    protected abstract List<K> _getAll();

    protected Query getQuery(String tableName, Map<String, Object> map) {
        StringBuilder sb = new StringBuilder("from "  + tableName + " where ");
        int iteration = 0;
        for(String key : map.keySet()) {
            if(iteration != 0) {
                sb.append(" AND ");
            }
            sb.append(key);
            sb.append(" = :");
            sb.append(key);
            iteration++;
        }
        Query q = getSession().createQuery(sb.toString());
        for(Map.Entry<String, Object> set : map.entrySet()) {
            q.setParameter(set.getKey(), set.getValue());
        }
        return q;
    }
}
