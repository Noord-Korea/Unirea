package handlertests;

import com.google.gson.Gson;
import com.restserver.handler.ArmyHandler;
import org.junit.Before;

public class ArmyHandlerTests {
    private ArmyHandler handler;
    private Gson gson = new Gson();
    @Before
    public void init() {
        handler = new ArmyHandler();
    }
}
