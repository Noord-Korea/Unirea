package handlertests;

import com.dbal.repository.TownRepository;
import com.restserver.handler.IAccountHandler;
import com.restserver.handler.ITownHandler;
import com.restserver.handler.TownHandler;
import org.junit.Before;
import org.junit.Test;
import repotests.AbstractRepoTest;

import java.util.logging.Level;

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
}
