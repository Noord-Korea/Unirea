import com.dbal.repository.PlayerRepository;
import com.dbal.specification.PlayerSpecification;
import com.models.Player;
import org.hibernate.Hibernate;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.Target;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.logging.Level;

import static org.junit.Assert.*;

public class PlayerRepoTest {
    private PlayerRepository repo;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void TestInitialize() {
        repo = new PlayerRepository();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        recreateDatabase();

    }

    private void recreateDatabase() {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SchemaExport schemaExport = new SchemaExport(serviceRegistry, configuration);
        schemaExport.create(Target.BOTH);
    }

    @Test
    public void testFindAll() {
        //database is being cleared at beginning of tests
        for (int i = 0; i < 10; i++) {
            repo.save(new Player("test" + i, "test1@gmail.com", "test1"));
        }
        int result = repo.findAll().size();
        assertEquals(10, result);
    }

    @Test
    public void testSave() {
        int before = repo.findAll().size();
        repo.save(new Player("wauw", "bas@gmail.com", "test"));
        int after = repo.findAll().size();
        assertNotEquals(before, after);
    }

    @Test
    public void testFindOne() {
        Player player = new Player("test", "test@gmail.com", "test");
        repo.save(player);
        Player result = repo.findOne(PlayerSpecification.getByUsername("test"));
        assertEquals(player.getId(), result.getId());
    }

    @Test
    public void testFineOneNull() {
        Player result = repo.findOne(1);
        assertEquals(null, result);
    }

    @Test
    public void testDelete() {
        Player player = new Player("test", "test@gmail.com", "test");
        repo.save(player);
        int reposize = repo.findAll().size();
        repo.delete(player);
        int reposizeafter = repo.findAll().size();
        assertEquals(reposize - 1, reposizeafter);
    }

    @Test
    public void testDeleteNonExistant() {
        repo.delete(1);
    }

}
