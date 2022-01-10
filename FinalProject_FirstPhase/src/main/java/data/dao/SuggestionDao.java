package data.dao;

import data.entities.Suggestion;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SuggestionDao {

    public int save(Suggestion suggestion) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(suggestion);
        transaction.commit();
        session.close();
        System.out.println("suggestion registered successfully");
        return id;
    }

    public void update(Suggestion suggestion) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(suggestion);
        transaction.commit();
        session.close();
        System.out.println("suggestion updated successfully");
    }

    public Suggestion load(int id) {
        Session session = HibernateUtils.getSession();
        Suggestion suggestion = session.get(Suggestion.class, id);
        session.close();
        System.out.println("suggestion loaded successfully");
        return suggestion;
    }

    public void delete(Suggestion suggestion) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(suggestion);
        transaction.commit();
        session.close();
        System.out.println("suggestion deleted successfully");
    }

}
