package data.dao;

import data.entities.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderDao {
    public int save(Order order) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(order);
        transaction.commit();
        session.close();
        System.out.println("order registered successfully");
        return id;
    }

    public void update(Order order) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
        session.close();
        System.out.println("order updated successfully");
    }

    public Order load(int id) {
        Session session = HibernateUtils.getSession();
        Order order = session.get(Order.class, id);
        session.close();
        System.out.println("order loaded successfully");
        return order;
    }

    public void delete(Order order) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(order);
        transaction.commit();
        session.close();
        System.out.println("order deleted successfully");
    }

}
