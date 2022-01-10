package data.dao;

import data.entities.SubService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SubServiceDao {

    public int save(SubService subService) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(subService);
        transaction.commit();
        session.close();
        System.out.println("sub service registered successfully");
        return id;
    }

    public void update(SubService subService) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(subService);
        transaction.commit();
        session.close();
        System.out.println("sub service updated successfully");
    }

    public SubService load(int id) {
        Session session = HibernateUtils.getSession();
        SubService subService = session.get(SubService.class, id);
        session.close();
        System.out.println("sub service loaded successfully");
        return subService;
    }

    public void delete(SubService subService) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(subService);
        transaction.commit();
        session.close();
        System.out.println("sub service deleted successfully");
    }
}
