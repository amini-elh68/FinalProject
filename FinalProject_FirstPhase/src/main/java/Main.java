import data.dao.HibernateUtils;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtils.getSession();
        session.close();
    }
}
