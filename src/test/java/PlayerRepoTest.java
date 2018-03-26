import com.dbal.repository.PlayerRepository;
import com.dbal.specification.PlayerSpecification;
import com.models.Player;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.logging.Level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PlayerRepoTest extends AbstractTest {
    private PlayerRepository repo;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void TestInitialize() {
        emptyTable("Player");
        repo = new PlayerRepository();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
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
    public void testFindOneUsername() {
        Player result = repo.findOne(PlayerSpecification.getByUsername("test"));
        assertEquals(null, result);
        Player player = new Player("test", "test@gmail.com", "test");
        repo.save(player);
        result = repo.findOne(PlayerSpecification.getByUsername("test"));
        assertEquals(player.getId(), result.getId());
    }

    @Test
    public void testFindOneNull() {
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
    public void testDeleteNonExistent() {
        exception.expect(IllegalArgumentException.class);
        repo.delete(1);
    }
}
