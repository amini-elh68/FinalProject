package data.dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory ourSessionFactory;

    public synchronized static SessionFactory getSessionFactory() {
        if (ourSessionFactory != null)
            return ourSessionFactory;
        else {
            ourSessionFactory = new Configuration().configure().buildSessionFactory();
            return ourSessionFactory;
        }

    }

    public static Session getSession() throws HibernateException {
        return getSessionFactory().openSession();
    }
}