package handlertests;

import com.dbal.repository.AccessTokenRepository;
import com.dbal.repository.PlayerRepository;
import com.dbal.repository.TownRepository;
import com.google.gson.Gson;
import com.models.Player;
import com.restserver.handler.AccountHandler;
import com.restserver.handler.ITownHandler;
import com.restserver.handler.TownHandler;
import com.restserver.json.request.account.Login;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;
import com.restserver.json.response.town.TownResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import repotests.AbstractRepoTest;

import java.time.LocalTime;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;

public class TownHandlerTest {
    private TownRepository townRepository;
    private ITownHandler handler;

    @Before
    public void testInitialize() {
        AbstractRepoTest.emptyTable("Town");
        AbstractRepoTest.emptyTable("Player");
        townRepository = new TownRepository();
        handler = new TownHandler(townRepository);
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    @Test    @Ignore

    public void testCreateTown() {
        PlayerRepository repo = new PlayerRepository();
        Player player = repo.save(new Player("testUser " + LocalTime.now(), "test@test.com", "testPass"));
        Reply reply = handler.createTown(player);
        assertEquals(Status.OK, reply.getStatus());
    }

    @Test    @Ignore

    public void testGetTownByAccessToken() {
        PlayerRepository repo = new PlayerRepository();
        Player player = repo.save(new Player("testUser " + LocalTime.now(), "test@test.com", "testPass"));
        AccountHandler accountHandler = new AccountHandler(new PlayerRepository(), new AccessTokenRepository());
        Reply reply = accountHandler.login(new Login("test@test.com", "testPass"));
        Assert.assertEquals(Status.OK, reply.getStatus());
        String token = reply.getMessage();
        reply = handler.createTown(player);
        Assert.assertEquals(Status.OK, reply.getStatus());
        Gson gson = new Gson();
        reply = handler.getTownByAccesstoken(token);
        TownResponse town = gson.fromJson(reply.getMessage(), TownResponse.class);
        Assert.assertNotNull(town);
    }
}