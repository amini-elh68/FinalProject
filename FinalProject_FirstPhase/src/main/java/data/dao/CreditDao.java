package data.dao;

import data.entities.Credit;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CreditDao {

    public int save(Credit credit) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(credit);
        transaction.commit();
        session.close();
        System.out.println("credit registered successfully");
        return id;
    }

    public void update(Credit credit) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(credit);
        transaction.commit();
        session.close();
        System.out.println("credit updated successfully");
    }

    public Credit load(int id) {
        Session session = HibernateUtils.getSession();
        Credit credit = session.get(Credit.class, id);
        session.close();
        System.out.println("credit loaded successfully");
        return credit;
    }

    public void delete(Credit credit) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(credit);
        transaction.commit();
        session.close();
        System.out.println("credit deleted successfully");
    }

}
