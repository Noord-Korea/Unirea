import com.dbal.repository.ClanRepository;
import com.dbal.specification.ClanSpecification;
import com.models.Clan;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.logging.Level;

public class ClanRepoTest extends AbstractRepoTest {

    private ClanRepository repo;
    private Clan clan;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void testInitialize() {
        emptyTable("Clan");
        repo = new ClanRepository();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    @Test
    public void testSaveClan() {
        int clans = repo.findAll().size();
        assertEquals(0, clans);

        insertClan();

        int clansAfter = repo.findAll().size();
        assertEquals(1, clansAfter);
    }

    private void insertClan() {
        clan = repo.save(new Clan("Clan"));
    }

    @Test
    public void testFindOneName() {
        Clan result = repo.findOne(ClanSpecification.getbyName("clan"));
        assertEquals(null, result);
        insertClan();
        result = repo.findOne(ClanSpecification.getbyName("clan"));
        assertEquals(clan.getId(), result.getId());
    }

    @Test
    public void testDeleteClan() {
        Clan clan = new Clan("Clan");

        repo.save(clan);
        int repoSize = repo.findAll().size();
        assertEquals(1, repoSize);

        repo.delete(clan);
        int repoSizeAfter = repo.findAll().size();
        assertEquals(0, repoSizeAfter);
    }

    @Test
    public void testDeleteNonExistent() {
        exception.expect(IllegalArgumentException.class);
        repo.delete(1);
    }

    @Test
    public void testFindOneByName() {
        insertClan();
        Clan result = repo.findOne(ClanSpecification.getbyName("Clan"));
        assertEquals(clan.getId(), result.getId());
    }
}
