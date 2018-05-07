import com.models.Player;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PlayerTest {

    private Player player;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void TestInitialize() {
    }

    @Test
    public void TestPlayerConstructorInvalidEmail() {
        exception.expect(IllegalArgumentException.class);
        player = new Player("test", "test", "test");
    }

    @Test
    public void TestPlayerConstructorValid() {
        player = new Player("test", "test@gmail.com", "test");
        assertEquals(player.getUsername(), "test");
        assertEquals(player.getEmail(), "test@gmail.com");
    }
}
