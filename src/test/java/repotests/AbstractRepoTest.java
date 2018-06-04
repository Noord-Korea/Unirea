package repotests;

import com.dbal.HibernateUtil;
import org.hibernate.Session;

public abstract class AbstractRepoTest {
    public static void emptyTable(String tableName){
        String query = String.format("DELETE FROM %s", tableName);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createQuery(query).executeUpdate();
        session.close();
    }
}
