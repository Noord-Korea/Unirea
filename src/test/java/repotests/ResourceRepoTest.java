package repotests;

import com.dbal.repository.ResourceRepository;
import com.dbal.specification.ResourceSpecification;
import com.models.Resource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import repotests.AbstractRepoTest;

import java.util.logging.Level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        int sizebefore = repo.findAll().size();
        insertResource();
        int sizeafter = repo.findAll().size();
        assertNotEquals(sizebefore, sizeafter);
    }

    @Test
    public void testFindOneByName() {
        Resource result = repo.findOne(ResourceSpecification.getByName("resource"));
        assertEquals(result, null);
        insertResource();
        Resource findresource = repo.findOne(ResourceSpecification.getByName("resource"));
        assertEquals(resource.getId(), findresource.getId());
    }

    @Test
    public void testDeleteResource() {
        insertResource();
        int reposizebefore = repo.findAll().size();
        repo.delete(resource);
        int reposizeafter = repo.findAll().size();
        assertEquals(reposizebefore - 1, reposizeafter);
    }

    @Test
    public void testDeleteNonExistent() {
        exception.expect(IllegalArgumentException.class);
        repo.delete(1);
    }


}
