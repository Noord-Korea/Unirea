import com.dbal.repository.PlayerRepository;
import com.models.Player;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.Assert.*;

public class PlayerRepoTest {
    private PlayerRepository repo;
    @BeforeEach
    public void TestInitialize() {
        repo = new PlayerRepository();
    }



    @Test
    public void saveTest() {
        int before = repo.findAll().size();
        repo.save(new Player("wauw", "bas@gmail.com", "test"));
        int after = repo.findAll().size();
        assertNotEquals(before, after);
    }
}
