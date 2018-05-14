import com.dbal.repository.ResourceRepository;
import com.dbal.specification.ResourceSpecification;
import com.models.Resource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.logging.Level;

import static org.junit.Assert.assertEquals;

public class ResourceRepoTest extends AbstractRepoTest {

    private ResourceRepository repo;
    private Resource resource;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void testInitialize() {
        emptyTable("Resource");
        repo = new ResourceRepository();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    private void insertResource() {
        resource = repo.save(new Resource("Resource"));
    }

    @Test
    public void testSaveResource() {
        int sizeBefore = repo.findAll().size();
        assertEquals(0, sizeBefore);

        insertResource();
        
        int sizeAfter = repo.findAll().size();
        assertEquals(1, sizeAfter);
    }

    @Test
    public void testFindOneByName() {
        Resource result = repo.findOne(ResourceSpecification.getByName("resource"));
        assertEquals(null, result);

        insertResource();

        Resource findResource = repo.findOne(ResourceSpecification.getByName("resource"));
        assertEquals(resource.getId(), findResource.getId());
    }

    @Test
    public void testDeleteResource() {
        insertResource();
        int repoSizeBefore = repo.findAll().size();
        assertEquals(1, repoSizeBefore);

        repo.delete(resource);

        int repoSizeAfter = repo.findAll().size();
        assertEquals(0, repoSizeAfter);
    }

    @Test
    public void testDeleteNonExistent() {
        exception.expect(IllegalArgumentException.class);
        repo.delete(1);
    }
}
