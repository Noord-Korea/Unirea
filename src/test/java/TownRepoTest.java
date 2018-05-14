import com.dbal.repository.TownRepository;
import com.dbal.specification.TownSpecification;
import com.models.Town;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.logging.Level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TownRepoTest extends AbstractRepoTest{
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
        int sizeBefore = repo.findAll().size();
        assertEquals(0, sizeBefore);

        insertTown();

        int sizeAfter = repo.findAll().size();
        assertNotEquals(1, sizeAfter);
    }

    @Test
    public void testFindOneByName() {
        Town result = repo.findOne(TownSpecification.getByName("town"));
        assertEquals(null, result);

        insertTown();

        Town findTown = repo.findOne(TownSpecification.getByName("town"));
        assertEquals(town.getId(), findTown.getId());
    }

    @Test
    public void testDeleteTown() {
        insertTown();
        int reposizeBefore = repo.findAll().size();
        assertEquals(1, reposizeBefore);

        repo.delete(town);

        int reposizeAfter = repo.findAll().size();
        assertEquals(0, reposizeAfter);
    }

    @Test
    public void testDeleteNonExistent() {
        exception.expect(IllegalArgumentException.class);
        repo.delete(1);
    }
}
