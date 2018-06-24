package handlertests;

import com.dbal.repository.PlayerRepository;
import com.dbal.repository.TownRepository;
import com.models.Player;
import com.restserver.handler.MapHandler;
import com.restserver.handler.TownHandler;
import com.restserver.json.response.town.TownPosition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class MapHandlerTests {
    private MapHandler mapHandler;

    @Before
    public void init() {
        mapHandler = new MapHandler();
    }

    @Test    @Ignore

    public void testGetAllTowns() {
        addPlayers(3);
        List<TownPosition> results = mapHandler.getAllTowns();
        Assert.assertEquals(6, results.size());
    }

    private void addPlayers(int amount) {
        PlayerRepository playerRepository = new PlayerRepository();
        for (int i = 1; i < amount + 1; i++) {
            Player player = new Player("username" + i, "email" + i + "@example.com", "password");
            playerRepository.save(player);
            TownHandler townHandler = new TownHandler(new TownRepository());
            for (int j = 0; j < i; j++) {
                townHandler.createTown(player);
            }
        }
    }
}