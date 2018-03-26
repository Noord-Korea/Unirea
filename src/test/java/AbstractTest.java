import com.dbal.HibernateUtil;
import org.hibernate.Session;

public abstract class AbstractTest {
    protected void emptyTable(String tableName){
        String query = String.format("DELETE FROM %s", tableName);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createQuery(query).executeUpdate();
        session.close();
    }
}
