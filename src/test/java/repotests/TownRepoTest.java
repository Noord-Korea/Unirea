package repotests;

import com.dbal.repository.TownRepository;
import com.dbal.specification.TownSpecification;
import com.models.Town;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import repotests.AbstractRepoTest;

import java.util.logging.Level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TownRepoTest extends AbstractRepoTest {
    private TownRepository repo;
    private Town town;

    @Rule
   public ExpectedException exception = ExpectedException.none();

    @Before
    public void testInitialize() {
        emptyTable("Town");
        repo = new TownRepository();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    private void insertTown() {
        town = repo.save(new Town("town"));
    }

    @Test
    public void testSaveTown() {
        int sizebefore = repo.findAll().size();
        insertTown();
        int sizeafter = repo.findAll().size();
        assertNotEquals(sizebefore, sizeafter);
    }

    @Test
    public void testFindOneByName() {
        Town result = repo.findOne(TownSpecification.getByName("town"));
        assertEquals(result, null);
        insertTown();
        Town findtown = repo.findOne(TownSpecification.getByName("town"));
        assertEquals(town.getId(), findtown.getId());
    }

    @Test
    public void testDeletetown() {
        insertTown();
        int reposizebefore = repo.findAll().size();
        repo.delete(town);
        int reposizeafter = repo.findAll().size();
        assertEquals(reposizebefore - 1, reposizeafter);
    }

    @Test
    public void testDeleteNonExistent() {
        exception.expect(IllegalArgumentException.class);
        repo.delete(1);
    }
}
