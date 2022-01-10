package data.dao;

import data.entities.Service;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ServiceDao {
    public int save(Service service) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(session);
        transaction.commit();
        session.close();
        System.out.println("service registered successfully");
        return id;
    }

    public void update(Service service) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(session);
        transaction.commit();
        session.close();
        System.out.println("service updated successfully");
    }

    public Service load(int id) {
        Session session = HibernateUtils.getSession();
        Service service = session.get(Service.class, id);
        session.close();
        System.out.println("service loaded successfully");
        return service;
    }

    public void delete(Service service) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(service);
        transaction.commit();
        session.close();
        System.out.println("service deleted successfully");
    }

    public List<Service> getServiceList() {
        Session session = HibernateUtils.getSession();
        Criteria criteria = session.createCriteria(Service.class);
        List<Service> services = criteria.list();
        session.close();
        return services;
    }
}
