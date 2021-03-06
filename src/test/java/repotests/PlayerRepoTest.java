package repotests;

import com.dbal.repository.PlayerRepository;
import com.dbal.specification.PlayerSpecification;
import com.models.Player;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.logging.Level;

import static org.junit.Assert.assertEquals;

public class PlayerRepoTest extends AbstractRepoTest {
    private PlayerRepository repo;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void TestInitialize() {
        emptyTable("Player");
        repo = new PlayerRepository();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }


    private void insertOneValidPlayer() {
        repo.save(new Player("test", "test@gmail.com", "test"));
    }

    @Test
    public void testSaveValid() {
        int before = repo.findAll().size();
        assertEquals(0, before);
        repo.save(new Player("wow", "bas@gmail.com", "test"));
        int after = repo.findAll().size();
        assertEquals(1, after);
    }


    @Test
    public void testFindOneUsername() {
        Player result = repo.findOne(PlayerSpecification.getByUsername("test"));
        assertEquals(null, result);
        Player player = new Player("test", "test@gmail.com", "test");
        repo.save(player);
        result = repo.findOne(PlayerSpecification.getByUsername("test"));
        assertEquals(player.getPlayerId(), result.getPlayerId());
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
        assertEquals(player.getPlayerId(), result.getPlayerId());
    }

    @Test
    public void testFindOneEmailEmpty() {
        insertOneValidPlayer();
        Player player = repo.findOne(PlayerSpecification.getByEmail(""));
        assertEquals(null, player);
    }

    @Test
    public void testFindOneEmailNull() {
        exception.expect(IllegalArgumentException.class);
        insertOneValidPlayer();
        Player player = repo.findOne(PlayerSpecification.getByEmail(null));
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
        int repoSize = repo.findAll().size();
        assertEquals(1, repoSize);

        repo.delete(player);
        int repoSizeAfter = repo.findAll().size();
        assertEquals(0, repoSizeAfter);
    }

    @Test
    public void testDeleteNonExistent() {
        exception.expect(IllegalArgumentException.class);
        repo.delete(1);
    }

}
