package data.dao;

import data.entities.Comment;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CommentDao {

    public int save(Comment comment) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(comment);
        transaction.commit();
        session.close();
        System.out.println("comment registered successfully");
        return id;
    }

    public void update(Comment comment) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(comment);
        transaction.commit();
        session.close();
        System.out.println("comment updated successfully");
    }

    public Comment load(int id) {
        Session session = HibernateUtils.getSession();
        Comment comment = session.get(Comment.class, id);
        session.close();
        System.out.println("comment loaded successfully");
        return comment;
    }

    public void delete(Comment comment) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(comment);
        transaction.commit();
        session.close();
        System.out.println("comment deleted successfully");
    }

}
