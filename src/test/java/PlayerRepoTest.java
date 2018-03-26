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

    private void insertOnePlayer() {
        repo.save(new Player("test", "test@gmail.com", "test"));
    }

    @Test
    public void testSaveValid() {
        int before = repo.findAll().size();
        repo.save(new Player("wauw", "bas@gmail.com", "test"));
        int after = repo.findAll().size();
        assertNotEquals(before, after);
    }

    @Test
    public void testSaveInvalidUsername() {
        Player player = repo.save(new Player("", "test@gmail.com", "test"));
        assertEquals(null, player);
    }
    @Test
    public void testSaveInvalidUsernameNull(){
        exception.expect(IllegalArgumentException.class);
        Player player = repo.save(new Player(null, "test@gmail.com", "test"));
    }

    @Test
    public void testSaveInvalidEmail() {
        Player player = repo.save(new Player("test", "test", "test"));
        assertEquals(null, player);
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
    public void testFindOneUsernameEmpty() {
        Player player = repo.findOne(PlayerSpecification.getByUsername(""));
        assertEquals(null, player);
    }

    @Test
    public void testFindOneEmail() {
        Player result = repo.findOne(PlayerSpecification.getByEmail("test@gmail.com"));
        assertEquals(null, result);
        Player player = new Player("test", "test@gmail.com", "test");
        repo.save(player);
        result = repo.findOne(PlayerSpecification.getByEmail("test@gmail.com"));
        assertEquals(player.getId(), result.getId());
    }

    @Test
    public void testFindOneEmailEmpty() {
        insertOnePlayer();
        Player player = repo.findOne(PlayerSpecification.getByEmail(""));
        assertEquals(null, player);
    }

    @Test
    public void testFindOneEmailNull() {
        insertOnePlayer();
        Player player = repo.findOne(PlayerSpecification.getByEmail(null));
        assertEquals(null, player);
    }

    @Test
    public void testFindAll() {
        assertEquals(0, repo.findAll().size());
        repo.save(new Player("test", "test@gmail.com", "test1"));
        assertEquals(1, repo.findAll().size());
        for (int i = 0; i < 10; i++) {
            repo.save(new Player("test" + i, "test" + i + "@gmail.com", "test1"));
        }
        int result = repo.findAll().size();
        assertEquals(11, result);
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
