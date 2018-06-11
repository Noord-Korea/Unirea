package handlertests;

import com.dbal.repository.AccessTokenRepository;
import com.dbal.repository.PlayerRepository;
import com.dbal.repository.TownRepository;
import com.models.Player;
import com.restserver.handler.IAccountHandler;
import com.restserver.handler.ITownHandler;
import com.restserver.handler.TownHandler;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;
import org.junit.Before;
import org.junit.Test;
import repotests.AbstractRepoTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TownHandlerTest {
    private TownRepository townRepository;
    private ITownHandler handler;

    @Before
    public void testInitialize() {
        AbstractRepoTest.emptyTable("Town");
        townRepository = new TownRepository();
        handler = new TownHandler(townRepository);
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    @Test
    public void testCreateTown() {
        PlayerRepository repo = new PlayerRepository();
        Player player = repo.save(new Player("testUser " + LocalTime.now(), "test@test.com", "testPass"));
        Reply reply = handler.createTown(player);
        assertEquals(Status.OK, reply.getStatus());
    }
}