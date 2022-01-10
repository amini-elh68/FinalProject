package data.dao;

import data.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

public class UserDao {

    public int save(User user) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(user);
        transaction.commit();
        session.close();
        System.out.println("user registered successfully");
        return id;
    }

    public void update(User user) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        System.out.println("user updated successfully");
        session.close();
    }

    public User load(int id) {
        Session session = HibernateUtils.getSession();
        User user = session.get(User.class, id);
        session.close();
        System.out.println("user loaded successfully");
        return user;
    }

    public User load(String email) {
        Session session = HibernateUtils.getSession();
        Query query = session.getNamedQuery("searchUserByEmail");
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();
        session.close();
        return user;
    }

    public void delete(User user) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(user);
        transaction.commit();
        session.close();
        System.out.println("user deleted successfully");
    }

}
